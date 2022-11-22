package com.enzoferrari.databases.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enzoferrari.databases.R;
import com.enzoferrari.databases.models.Task;
import com.enzoferrari.databases.services.TaskDAO;

import java.util.List;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.TaskViewHolder> {
  private Context context;
  List<Task> tasks;
  TaskDAO taskDao;

  public AdapterTask(Context context, List<Task> tasks, TaskDAO task ) {
    this.context = context;
    this.tasks = tasks;
    this.taskDao = task;
  }

  @NonNull
  @Override
  public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, parent, false);

    TaskViewHolder holder = new TaskViewHolder(item);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull TaskViewHolder holder, int index) {
    holder.name.setText(tasks.get(index).name);
    holder.description.setText(tasks.get(index).description);
    holder.checkbox.setChecked(tasks.get(index).done);
    holder.checkbox.setOnClickListener(v -> {
      ContentValues values = new ContentValues();
      Task task = tasks.get(index);
      values.put("name", task.name);
      values.put("description", task.description);
      values.put("done", !task.done);
      taskDao.update(values, index + 1);
    });
  }

  @Override
  public int getItemCount() {
    return tasks.size();
  }

  class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView name = itemView.findViewById(R.id.task_card_name);
    TextView description = itemView.findViewById(R.id.task_card_description);
    CheckBox checkbox = itemView.findViewById(R.id.task_card_checkbox);

    public TaskViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
