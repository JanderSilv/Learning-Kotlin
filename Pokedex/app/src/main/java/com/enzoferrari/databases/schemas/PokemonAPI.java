package com.enzoferrari.databases.schemas;

import java.io.Serializable;
import java.util.List;

public class PokemonAPI implements Serializable {
    public Integer id;
    public String name;
    public List<MovesItemAPI> moves;
    public SpritesAPI sprites;
    public List<TypesItemAPI> types;
}
