package com.enzoferrari.databases.models;

import java.io.Serializable;

public class Pokemon implements Serializable {
    public Integer id;
    public String name;
    public String types;
    public String abilities;
    public String image_url;
    public boolean isFavorite;

    public Pokemon(Integer id, String name, String types, String abilities, String image_url, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.abilities = abilities;
        this.image_url = image_url;
        this.isFavorite = isFavorite;
    }
}