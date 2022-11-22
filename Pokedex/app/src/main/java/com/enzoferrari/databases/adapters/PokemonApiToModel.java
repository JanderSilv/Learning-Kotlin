package com.enzoferrari.databases.adapters;

import com.enzoferrari.databases.models.Pokemon;
import com.enzoferrari.databases.schemas.MovesItemAPI;
import com.enzoferrari.databases.schemas.PokemonAPI;
import com.enzoferrari.databases.schemas.TypesItemAPI;

import java.util.List;

public class PokemonApiToModel {
    public Pokemon adapt(PokemonAPI pokemonAPI) {
        String types = buildTypes(pokemonAPI.types);
        String abilities = buildAbilities(pokemonAPI.moves);

        return new Pokemon(
                pokemonAPI.id,
                pokemonAPI.name,
                types,
                abilities,
                pokemonAPI.sprites.front_default,
                false
        );
    }

    private String buildAbilities(List<MovesItemAPI> moves) {
        StringBuilder typesBuilder = new StringBuilder();

        for(MovesItemAPI movesItem : moves ) {
            typesBuilder
                    .append(movesItem.move.name)
                    .append(',');
        }

        trimEndBy(typesBuilder, ',');
        return typesBuilder.toString();
    }

    private String buildTypes(List<TypesItemAPI> types) {
        StringBuilder typesBuilder = new StringBuilder();

        for(TypesItemAPI typeItem : types ) {
            typesBuilder
                    .append(typeItem.type.name)
                    .append(',');
        }

        trimEndBy(typesBuilder, ',');
        return typesBuilder.toString();
    }

    private void trimEndBy(StringBuilder typesBuilder, char character) {
        int stringEndIndex = typesBuilder.length() - 1;
        if (typesBuilder.charAt(stringEndIndex) == character) {
            typesBuilder.deleteCharAt(stringEndIndex);
        }
    }
}
