package com.enzoferrari.databases.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.enzoferrari.databases.FavoritesActivity;
import com.enzoferrari.databases.PokemonActivity;
import com.enzoferrari.databases.R;
import com.enzoferrari.databases.models.Pokemon;
import com.enzoferrari.databases.services.PokemonDAO;
import com.enzoferrari.databases.utilities.Utilities;

import java.util.List;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.TaskViewHolder> {
    private Context context;
    List<Pokemon> pokemons;
    PokemonDAO pokemonDao;

    public AdapterPokemon(Context context, List<Pokemon> pokemons, PokemonDAO pokemon) {
        this.context = context;
        this.pokemons = pokemons;
        this.pokemonDao = pokemon;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_card, parent, false);

        TaskViewHolder holder = new TaskViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int index) {
        try {
            Utilities utilities = new Utilities();
            Pokemon pokemon = pokemons.get(index);
            String pokemonNumber = "N° " + pokemon.id;

            Glide.with(this.context).load(pokemon.image_url).into(holder.image);
            String pokemonName = pokemon.name;
            holder.name.setText(utilities.capitalize(pokemonName));
            holder.num.setText(pokemonNumber);

            String[] types = pokemon.types.split(",");
            holder.type_1.setText(utilities.capitalize(types[0]));
            if (types.length >= 2) {
                holder.type_2.setText(utilities.capitalize(types[1]));
                holder.type_2.setVisibility(View.VISIBLE);
            }

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.putExtra("pokemon", pokemon);
                context.startActivity(intent);
            });

        } catch (Exception error) {
            System.out.println("ViewHolderException: " + error);
        }
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView name = itemView.findViewById(R.id.pokemon_name);
        TextView num = itemView.findViewById(R.id.pokemon_number);
        ImageView image = itemView.findViewById(R.id.pokemon_image);
        TextView type_1 = itemView.findViewById(R.id.pokemon_type_1);
        TextView type_2 = itemView.findViewById(R.id.pokemon_type_2);

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
