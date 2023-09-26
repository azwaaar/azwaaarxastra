package com.agit.azwarxagit.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agit.azwarxagit.R;
import com.agit.azwarxagit.adapters.PokemonAdapter;
import com.agit.azwarxagit.databases.PokemonDatabase;
import com.agit.azwarxagit.models.Pokemon;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private PokemonAdapter pokemonAdapter;
    private ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();


    Gson gson;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pokemonRV)
    RecyclerView pokemonRV;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.filter)
    CheckBox filter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.searchBar)
    EditText searchBar;

    PokemonDatabase pokemonDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gson = new GsonBuilder().create();

        pokemonAdapter = new PokemonAdapter(this);
        pokemonDatabase = PokemonDatabase.getInstance(this);

        getPokemonList();
    }

    private void getPokemonList() {
        String myUrl = "https://pokeapi.co/api/v2/pokemon/";
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    try {
                        // Create a JSON object containing information from the API.
                        JSONObject jsonObject = new JSONObject(response);

                        Type type = new TypeToken<List<Pokemon>>() {
                        }.getType();

                        pokemonArrayList = gson.fromJson(jsonObject.getString("results"), type);

                        pokemonRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                        pokemonAdapter = new PokemonAdapter(this);

                        pokemonAdapter.setPokemonList(pokemonArrayList);
                        pokemonRV.setAdapter(pokemonAdapter);

                        // sort
                        filter.setOnCheckedChangeListener((compoundButton, b) -> {
                            if (b) {
                                Log.e("AAAA", "CLICK");
                                sortArray();
                            } else {
                                desArray();
                            }
                        });

                        // search
                        searchBar.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                                // filter your list from your input
                                filter(s.toString());
                            }
                        });

                        // save to database
                        for (Pokemon p : pokemonArrayList) {
                            Long pokemonId = pokemonDatabase.pokemonDAO().pokemonData(p);
                            pokemonDatabase.pokemonDAO().findById(pokemonId);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }

    private void desArray() {

        Collections.sort(pokemonArrayList, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon pokemon, Pokemon t1) {
                return t1.getName().compareTo(pokemon.getName());
            }
        });

        // update recyclerview
        pokemonAdapter.updateList(pokemonArrayList);
    }

    private void sortArray() {

        Collections.sort(pokemonArrayList, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon pokemon, Pokemon t1) {
                return pokemon.getName().compareTo(t1.getName());
            }
        });

        // update recyclerview
        pokemonAdapter.updateList(pokemonArrayList);
    }

    void filter(String text) {
        ArrayList<Pokemon> temp = new ArrayList<>();
        for (Pokemon d : pokemonArrayList) {
            if (d.getName().contains(text)) {
                temp.add(d);
            }
        }
        // update recyclerview
        pokemonAdapter.updateList(temp);
    }
}