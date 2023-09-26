package com.agit.azwarxagit.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.agit.azwarxagit.models.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {
    private static PokemonDatabase pokemonDatabase;

    public abstract PokemonDAO pokemonDAO();

    public static PokemonDatabase getInstance(Context context) {
        synchronized (PokemonDatabase.class) {
            if (pokemonDatabase == null) {
                pokemonDatabase = Room.databaseBuilder(context, PokemonDatabase.class, "pokemon").allowMainThreadQueries().build();
            }
        }
        return pokemonDatabase;
    }
}
