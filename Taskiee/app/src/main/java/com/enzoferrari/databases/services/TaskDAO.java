package com.enzoferrari.databases.services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.enzoferrari.databases.data.Connection;
import com.enzoferrari.databases.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private final Connection connection;

    public TaskDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection getConnection() {
        return this.connection;
    }

    private SQLiteDatabase getDatabase() {
        return this
                .getConnection()
                .getWritableDatabase();
    }

    public long create(Task task) {
        ContentValues values = new ContentValues();

        values.put("name", task.name);
        values.put("description", task.description);
        values.put("done", false);

        return this
                .getDatabase()
                .insert("tasks", null, values);
    }

    public long update(ContentValues values, Integer id) {
        return this
                .getDatabase()
                .update("tasks", values, "id = ?", new String[]{id.toString()});
    }

    public List<Task> getAll() {
        Cursor queryCursor = this
                .getDatabase()
                .query(
                        "tasks",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        List<Task> tasks = new ArrayList<>();

        if (queryCursor.moveToFirst()) {
            while (!queryCursor.isAfterLast()) {
                tasks.add(buildTaskFromCursor(queryCursor));
                queryCursor.moveToNext();
            }
        }

        return tasks;
    }

    @SuppressLint("Range")
    @NonNull
    private Task buildTaskFromCursor(@NonNull Cursor queryCursor) {
        @SuppressLint("Range") Integer id = queryCursor.getInt(queryCursor.getColumnIndex("id"));
        @SuppressLint("Range") String name = queryCursor.getString(queryCursor.getColumnIndex("name"));
        @SuppressLint("Range") String description = queryCursor.getString(queryCursor.getColumnIndex("description"));
        @SuppressLint("Range") boolean done = queryCursor.getInt(queryCursor.getColumnIndex("done")) > 0;

        return new Task(id, name, description, done);
    }
}
