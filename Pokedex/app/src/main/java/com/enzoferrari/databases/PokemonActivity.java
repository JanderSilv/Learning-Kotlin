package com.enzoferrari.databases;

import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.enzoferrari.databases.factories.PokemonDAOFactory;
import com.enzoferrari.databases.models.Pokemon;
import com.enzoferrari.databases.services.PokemonDAO;
import com.enzoferrari.databases.utilities.Utilities;

public class PokemonActivity extends AppCompatActivity {

    private PokemonDAO pokemonDAO;
    private Pokemon pokemonState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        this.pokemonDAO = PokemonDAOFactory.build(this);

        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);

        Bundle extras = getIntent().getExtras();
        if (extras == null) startActivity(mainActivityIntent);
        Utilities utilities = new Utilities();
        Pokemon pokemon = (Pokemon) extras.getSerializable("pokemon");
        this.pokemonState = pokemon;

        ImageButton favoriteButton = findViewById(R.id.pokemon_favorite);
        ImageView pokemonImage = findViewById(R.id.pokemon_image);
        TextView pokemonNameView = findViewById(R.id.pokemon_name);
        TextView pokemonNumberView = findViewById(R.id.pokemon_number);
        TextView pokemonType1 = findViewById(R.id.pokemon_type_1);
        TextView pokemonType2 = findViewById(R.id.pokemon_type_2);

        String pokemonNumber = "N° " + pokemonState.id;
        String[] types = pokemonState.types.split(",");

        if (pokemonState.isFavorite) favoriteButton.setImageResource(star_big_on);
        else favoriteButton.setImageResource(star_big_off);

        Glide.with(this).load(pokemonState.image_url).into(pokemonImage);
        pokemonNameView.setText(utilities.capitalize(pokemonState.name));
        pokemonNumberView.setText(pokemonNumber);
        pokemonType1.setText(utilities.capitalize(types[0]));
        if (types.length >= 2) {
            pokemonType2.setText(utilities.capitalize(types[1]));
            pokemonType2.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.pokemon_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivityIntent);
            }
        });

        findViewById(R.id.pokemon_favorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();

                String pokemonName = utilities.capitalize(pokemon.name);
                Boolean pokemonIsFavorite = !pokemonState.isFavorite;
                pokemonState.isFavorite = pokemonIsFavorite;

                values.put("id", pokemonState.id);
                values.put("image_url", pokemonState.image_url);
                values.put("name", pokemonState.name);
                values.put("abilities", pokemonState.abilities);
                values.put("isFavorite", pokemonIsFavorite);
                values.put("types", pokemonState.types);
                pokemonDAO.update(values, pokemonState.id);

                if (!pokemonIsFavorite) {
                    favoriteButton.setImageResource(star_big_off);
                    Toast.makeText(
                            view.getContext(),
                            pokemonName + " removed from favorites",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    favoriteButton.setImageResource(star_big_on);
                    Toast.makeText(
                            view.getContext(),
                            pokemonName + " is favorite",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}