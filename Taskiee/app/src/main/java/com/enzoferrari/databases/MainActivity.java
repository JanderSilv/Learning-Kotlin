package com.enzoferrari.databases;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enzoferrari.databases.adapters.AdapterTask;
import com.enzoferrari.databases.factories.TaskDAOFactory;
import com.enzoferrari.databases.models.Task;
import com.enzoferrari.databases.services.TaskDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private EditText taskName;
    private EditText taskDescription;
    private SearchView searchView;

    private TaskDAO taskDAO;
    private List<Task> taskCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.taskName = findViewById(R.id.taskName);
        this.taskDescription = findViewById(R.id.taskDescription);
        this.searchView = findViewById(R.id.search_view);

        this.taskDAO = TaskDAOFactory.build(this);
        this.taskCards = new ArrayList<>(this.taskDAO.getAll());



        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String s) {
               renderTaskCard(
                       taskCards
                               .stream()
                               .filter(task -> task
                                       .name
                                       .toLowerCase(Locale.ROOT)
                                       .contains(s.toLowerCase(Locale.ROOT)))
                               .collect(Collectors.toList())
               );

               return true;
            }
        });

        renderTaskCard(this.taskCards);
    }

    public void createTask(View view) {
        String name = taskName
                .getText()
                .toString();

        boolean isFieldEmpty = name.trim().equalsIgnoreCase("");
        if (isFieldEmpty) {
            taskName.setError("Esse campo não pode ser vazio.");
            return;
        }

        String description = taskDescription
                .getText()
                .toString();

        Task task = new Task(name, description);
        long taskId = taskDAO.create(task);

        Toast.makeText(
                this,
                "Task created with id: " + taskId,
                Toast.LENGTH_SHORT
        ).show();

        clearInputs();
        hideKeyboard();

        this.taskCards.add(task);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder windowToken = this.taskName.getWindowToken();
        inputManager.hideSoftInputFromWindow(windowToken,0);
    }

    private void clearInputs() {
        taskName.setText("");
        taskDescription.setText("");
    }

    private void renderTaskCard(List<Task> tasks) {
        RecyclerView recyclerView = findViewById(R.id.recycle_view_tasks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterTask adapterTask = new AdapterTask(this, tasks, taskDAO);

        recyclerView.setAdapter(adapterTask);
    }
}