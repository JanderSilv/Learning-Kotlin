package com.enzoferrari.databases.factories;

import android.content.Context;

import com.enzoferrari.databases.data.Connection;
import com.enzoferrari.databases.services.TaskDAO;

public class TaskDAOFactory {
    public static TaskDAO build(Context context) {
        return new TaskDAO(new Connection(context));
    }
}
