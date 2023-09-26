package com.agit.azwarxagit.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.agit.azwarxagit.R;
import com.agit.azwarxagit.details.DetailPokemon;
import com.agit.azwarxagit.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.myPokemon> {
    private static Context context;
    private static ArrayList<Pokemon> pokemonArrayList;

    public PokemonAdapter(Context context) {
        PokemonAdapter.context = context;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonArrayList) {
        PokemonAdapter.pokemonArrayList = pokemonArrayList;
    }

    @NonNull
    @Override
    public PokemonAdapter.myPokemon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

        return new myPokemon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.myPokemon holder, int position) {
        final Pokemon pokemon = pokemonArrayList.get(position);

        holder.pokemonName.setText(pokemon.getName() != null ? pokemon.getName() : "N/A");

        holder.listPokemon.setOnClickListener(v -> {
            Intent send = new Intent(context, DetailPokemon.class);
            Bundle b = new Bundle();
            b.putSerializable("serial_pokemon", pokemon);
            send.putExtras(b);
            context.startActivity(send);
        });
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public void updateList(ArrayList<Pokemon> list){
        pokemonArrayList = list;
        notifyDataSetChanged();
    }

    public static class myPokemon extends RecyclerView.ViewHolder {
        private final TextView pokemonName;
        private final ConstraintLayout listPokemon;
        public myPokemon(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemonName);
            listPokemon = itemView.findViewById(R.id.listPokemon);
        }
    }
}
