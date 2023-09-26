package com.agit.azwarxagit.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agit.azwarxagit.R;
import com.agit.azwarxagit.adapters.AbilityAdapter;
import com.agit.azwarxagit.models.Abilities;
import com.agit.azwarxagit.models.Pokemon;
import com.agit.azwarxagit.models.PokemonDetail;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPokemon extends AppCompatActivity {

    ActionBar actionBar;

    private AbilityAdapter abilityAdapter;
    private ArrayList<Abilities> pokemonDetailArrayList = new ArrayList<>();

    Gson gson;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.abilityRV)
    RecyclerView abilityRV;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pokemonImg)
    ImageView pokemonImg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.posterImg)
    ImageView posterImg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pokemonName)
    TextView pokemonName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pokemonWeight)
    TextView pokemonWeight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pokemonHeight)
    TextView pokemonHeight;

    Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pokemon);
        ButterKnife.bind(this);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Pokemon Detail");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        pokemon = (Pokemon) getIntent().getSerializableExtra("serial_pokemon");

        gson = new GsonBuilder().create();
        abilityAdapter = new AbilityAdapter(this);

        getAbility();
        getName();
        getImageAndPoster();
    }

    private void getImageAndPoster() {
        String myUrl = pokemon.getUrl();
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    Type type = new TypeToken<PokemonDetail>() {
                    }.getType();

                    PokemonDetail p = gson.fromJson(response, type);

                    pokemonWeight.setText("Weight: " + p.getWeight());
                    pokemonHeight.setText("Height: " + p.getHeight());

                    String image = p.getSprites().getOther().getHome().getFront_default();
                    String poster = p.getSprites().getOther().getHome().getFront_shiny();

                    Glide.with(this)
                            .load(image)
                            .placeholder(R.drawable.pokemon)
                            .into(pokemonImg);

                    Glide.with(this)
                            .load(poster)
                            .placeholder(R.drawable.pikachu)
                            .into(posterImg);

                },
                volleyError -> Toast.makeText(DetailPokemon.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }

    private void getName() {
        String myUrl = pokemon.getUrl();
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {

                    try {
                        // Create a JSON object containing information from the API.
                        JSONObject jsonObject = new JSONObject(response);

                        Type type = new TypeToken<PokemonDetail>() {
                        }.getType();

                        PokemonDetail p = gson.fromJson(jsonObject.getString("species"), type);
                        pokemonName.setText(p.getName().toUpperCase());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(DetailPokemon.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }

    private void getAbility() {
        String myUrl = pokemon.getUrl();
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    try {
                        Log.e("URL", "URL " + response);
                        // Create a JSON object containing information from the API.
                        JSONObject jsonObject = new JSONObject(response);

                        Type type = new TypeToken<List<Abilities>>() {
                        }.getType();

                        pokemonDetailArrayList = gson.fromJson(jsonObject.getString("abilities"), type);
                        Log.e("R", "RR: " + pokemonDetailArrayList);
                        abilityRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                        abilityAdapter = new AbilityAdapter(this);

                        abilityAdapter.setAbilityList(pokemonDetailArrayList);
                        abilityRV.setAdapter(abilityAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(DetailPokemon.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}