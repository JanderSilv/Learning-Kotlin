package com.enzoferrari.databases.services;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PokeAPI {
    public Integer id;
    public String name;
    public String types;
    public String abilities;
    public String image_url;

    public PokeAPI() {
        this.id = null;
        this.name = null;
        this.types = "";
        this.abilities = "";
        this.image_url = null;
    }

    public PokeAPI(String name) throws Exception {
        try {
            this.buscar(name);
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public final void buscar(String name) throws Exception, JSONException {
        this.name = name;

        String url = "https://pokeapi.co/api/v2/pokemon/" + name;

        JSONObject obj = null;
        try {
            obj = new JSONObject(this.get(url));
        } catch(JSONException e) {
            e.printStackTrace();
        }

        if (!obj.has("erro")) {
            this.id = Integer.parseInt(obj.getString("id"));

            this.name = obj.getString("name");

            this.types = obj.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");
            for(int i = 1; i < obj.getJSONArray("types").length(); i++)
                this.types += ", " + obj.getJSONArray("types").getJSONObject(i).getJSONObject("type").getString("name");

            this.abilities = obj.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name");
            for(int i = 1; i < obj.getJSONArray("abilities").length(); i++)
                this.abilities += ", " + obj.getJSONArray("abilities").getJSONObject(i).getJSONObject("ability").getString("name");

            this.image_url = obj.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default");
        } else {
            throw new Exception("Não foi possível encontrar o pokemon");
        }
    }

    public final String get(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException | ProtocolException ex) {
            throw new Exception(ex.getMessage());
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        }
        return result.toString();
    }
}