package com.enzoferrari.databases.models;

import java.io.Serializable;

public class Task implements Serializable {
    public Integer id;
    public String name;
    public String description;
    public boolean done;

    public Task(String name, String description) {
        this(null, name, description, false);
    }

    public Task(Integer id, String name, String description, boolean done) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.done = done;
    }
}
