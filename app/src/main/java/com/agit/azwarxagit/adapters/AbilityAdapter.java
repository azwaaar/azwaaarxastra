package com.agit.azwarxagit.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agit.azwarxagit.R;
import com.agit.azwarxagit.models.Abilities;
import com.agit.azwarxagit.models.Ability;
import com.agit.azwarxagit.models.Pokemon;
import com.agit.azwarxagit.models.PokemonDetail;

import java.util.ArrayList;

public class AbilityAdapter extends RecyclerView.Adapter<AbilityAdapter.myAbility> {
    private static Context context;
    private static ArrayList<Abilities> pokemonDetailArrayList;

    public AbilityAdapter(Context context) {
        AbilityAdapter.context = context;
    }

    public void setAbilityList(ArrayList<Abilities> pokemonDetailArrayList) {
        AbilityAdapter.pokemonDetailArrayList = pokemonDetailArrayList;
    }

    @NonNull
    @Override
    public AbilityAdapter.myAbility onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ability, parent, false);

        return new myAbility(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbilityAdapter.myAbility holder, int position) {
        final Abilities pokemonDetail = pokemonDetailArrayList.get(position);

        holder.abilityName.setText(pokemonDetail.getAbility().getName() != null ? pokemonDetail.getAbility().getName() : "N/A");
    }

    @Override
    public int getItemCount() {
        return pokemonDetailArrayList.size();
    }

    public static class myAbility extends RecyclerView.ViewHolder {
        private final TextView abilityName;

        public myAbility(@NonNull View itemView) {
            super(itemView);
            abilityName = itemView.findViewById(R.id.abilityName);
        }
    }
}
