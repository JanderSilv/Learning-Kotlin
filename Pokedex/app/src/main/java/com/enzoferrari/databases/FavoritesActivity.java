package com.enzoferrari.databases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.enzoferrari.databases.adapters.AdapterPokemon;
import com.enzoferrari.databases.factories.PokemonDAOFactory;
import com.enzoferrari.databases.models.Pokemon;
import com.enzoferrari.databases.services.PokemonDAO;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private PokemonDAO pokemonDAO;
    private List<Pokemon> pokemonCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        this.pokemonDAO = PokemonDAOFactory.build(this);
        this.pokemonCards = new ArrayList<>(this.pokemonDAO.getFavorites());

        renderPokemonCard(pokemonCards);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void renderPokemonCard(List<Pokemon> pokemons) {
        RecyclerView recyclerView = findViewById(R.id.recycle_view_pokemons);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterPokemon adapterPokemon = new AdapterPokemon(this, pokemons, pokemonDAO);

        recyclerView.setAdapter(adapterPokemon);
    }
}