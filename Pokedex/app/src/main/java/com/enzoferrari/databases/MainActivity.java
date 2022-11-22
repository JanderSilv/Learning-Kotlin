package com.enzoferrari.databases;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enzoferrari.databases.adapters.AdapterPokemon;
import com.enzoferrari.databases.factories.PokemonDAOFactory;
import com.enzoferrari.databases.models.Pokemon;
import com.enzoferrari.databases.services.PokeAPI;
import com.enzoferrari.databases.services.PokemonDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText pokemonName;

    private PokemonDAO pokemonDAO;
    private AdapterPokemon pokemonAdapter ;
    private List<Pokemon> pokemonCards;

    private MainActivity ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.pokemonName = findViewById(R.id.pokemon_name_input);

        this.pokemonDAO = PokemonDAOFactory.build(this);
        this.pokemonCards = new ArrayList<>(this.pokemonDAO.getAll());

        findViewById(R.id.favorites_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.search_pokemon_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPokemon();
            }
        });

        renderPokemonCard(this.pokemonCards);
    }

    public void createPokemon() {
        String name = pokemonName
                .getText()
                .toString().toLowerCase();

        boolean isFieldEmpty = name.trim().equalsIgnoreCase("");
        if (isFieldEmpty) {
            pokemonName.setError("Esse campo não pode ser vazio.");
            return;
        }

        new DownloadPokemon().execute(name);

        clearInputs();
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder windowToken = this.pokemonName.getWindowToken();
        inputManager.hideSoftInputFromWindow(windowToken,0);
    }

    private void clearInputs() {
        pokemonName.setText("");
    }

    private void renderPokemonCard(List<Pokemon> pokemons) {
        RecyclerView recyclerView = findViewById(R.id.recycle_view_pokemons);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterPokemon adapterPokemon = new AdapterPokemon(this, pokemons, pokemonDAO);
        this.pokemonAdapter = adapterPokemon;

        recyclerView.setAdapter(adapterPokemon);
    }

    private class DownloadPokemon extends AsyncTask<String, Void, PokeAPI> {
        @Override
        protected PokeAPI doInBackground(String ... names) {
            PokeAPI api = null;

            try {
                api = new PokeAPI(names[0]);
            } finally {
                return api;
            }
        }

        @Override
        protected void onPostExecute(PokeAPI result) {
            if (result != null) {
                Pokemon pokemon = new Pokemon(result.id, result.name, result.types, result.abilities, result.image_url, false);
                long pokemonId = pokemonDAO.create(pokemon);

                Toast.makeText(
                        ctx,
                        "Pokemon created with id: " + pokemonId,
                        Toast.LENGTH_SHORT
                ).show();

                ctx.pokemonCards.add(pokemon);
                ctx.pokemonAdapter.notifyDataSetChanged();
            }
        }
    }
}