package com.enzoferrari.databases.services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.enzoferrari.databases.data.Connection;
import com.enzoferrari.databases.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
    private final Connection connection;

    public PokemonDAO(Connection connection) {
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

    public long create(Pokemon pokemon) {
        ContentValues values = new ContentValues();

        values.put("id", pokemon.id);
        values.put("name", pokemon.name);
        values.put("types", pokemon.types);
        values.put("abilities", pokemon.abilities);
        values.put("image_url", pokemon.image_url);
        values.put("isFavorite", pokemon.isFavorite);

        return this
                .getDatabase()
                .insert("pokemons", null, values);
    }

    public long update(ContentValues values, Integer id) {
        return this
                .getDatabase()
                .update("pokemons", values, "id = ?", new String[]{ id.toString() });
    }

    public List<Pokemon> getFavorites() {
        Cursor queryCursor = this
                .getDatabase()
                .query(
                        "pokemons",
                        null,
                        "isFavorite = ?",
                        new String[]{"1"},
                        null,
                        null,
                        null);

        return buildPokemonList(queryCursor);
    }

    public List<Pokemon> getAll() {
        Cursor queryCursor = this
                .getDatabase()
                .query(
                        "pokemons",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        return buildPokemonList(queryCursor);
    }

    @NonNull
    private List<Pokemon> buildPokemonList(Cursor queryCursor) {
        List<Pokemon> pokemons = new ArrayList<>();

        if (queryCursor.moveToFirst()) {
            while (!queryCursor.isAfterLast()) {
                pokemons.add(buildPokemonsFromCursor(queryCursor));
                queryCursor.moveToNext();
            }
        }

        return pokemons;
    }

    @SuppressLint("Range")
    @NonNull
    private Pokemon buildPokemonsFromCursor(@NonNull Cursor queryCursor) {
        @SuppressLint("Range") Integer id = queryCursor.getInt(queryCursor.getColumnIndex("id"));
        @SuppressLint("Range") String name = queryCursor.getString(queryCursor.getColumnIndex("name"));
        @SuppressLint("Range") String types = queryCursor.getString(queryCursor.getColumnIndex("types"));
        @SuppressLint("Range") String abilities = queryCursor.getString(queryCursor.getColumnIndex("abilities"));
        @SuppressLint("Range") String image_url = queryCursor.getString(queryCursor.getColumnIndex("image_url"));
        @SuppressLint("Range") boolean isFavorite = queryCursor.getInt(queryCursor.getColumnIndex("isFavorite")) > 0;

        return new Pokemon(id, name, types, abilities, image_url, isFavorite);
    }
}
