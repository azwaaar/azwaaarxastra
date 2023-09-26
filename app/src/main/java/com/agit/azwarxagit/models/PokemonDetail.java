package com.agit.azwarxagit.models;

import java.io.Serializable;
import java.util.List;

public class PokemonDetail implements Serializable {
    private List<Abilities> abilities;
    private Sprites sprites;
    private String name;
    private String height;
    private String weight;

    public PokemonDetail(List<Abilities> abilities, Sprites sprites, String name, String height, String weight) {
        this.abilities = abilities;
        this.sprites = sprites;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PokemonDetail{" +
                "abilities=" + abilities +
                ", sprites=" + sprites +
                ", name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
