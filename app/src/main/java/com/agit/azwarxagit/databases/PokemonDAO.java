package com.agit.azwarxagit.databases;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.agit.azwarxagit.models.Pokemon;

@Dao
public interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long pokemonData(Pokemon user);

    @Query("SELECT * FROM pokemon WHERE id NOTNULL")
    Pokemon[] allPokemonDAO();

    // id
    @Query("SELECT * FROM pokemon WHERE id  == :id")
    Pokemon findById(Long id);

    // getAll
    @Query("SELECT * FROM pokemon")
    Pokemon[] getAll();

    @Query("SELECT * FROM pokemon WHERE id NOTNULL")
    Cursor CURSOR();

    @Delete
    void deleteItem(Pokemon user);
}
