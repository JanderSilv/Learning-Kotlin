package com.enzoferrari.databases.factories;

import android.content.Context;

import com.enzoferrari.databases.data.Connection;
import com.enzoferrari.databases.services.PokemonDAO;

public class PokemonDAOFactory {
    public static PokemonDAO build(Context context) {
        return new PokemonDAO(new Connection(context));
    }
}
