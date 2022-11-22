package com.enzoferrari.databases.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class Connection extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "taskDatabase.db";

    public Connection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS tasks("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "name TEXT,"
                        + "description TEXT,"
                        + "done BOOLEAN)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
