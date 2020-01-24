package com.example.bakingapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bakingapp.data.remote.RecipeApiService;
import com.squareup.moshi.Moshi;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class DefaultRepository {
    private MutableLiveData<List<Recipe>> recipes;
    private RecipeApiService api;

    /**
     * get receips from remote server and save it locally in a mutable live data.
     */
    public DefaultRepository() {
        Log.d("DefaultRepository", "created");
        recipes = new MutableLiveData<List<Recipe>>();

        String RECIPE_BASE_URL = "http://120.77.95.13";


        Moshi moshi = new Moshi.Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(RECIPE_BASE_URL)
                .addConverterFactory
                        (MoshiConverterFactory.create(moshi))
                .build();

        api = retrofit.create(RecipeApiService.class);

        api.getRecipesAsync().enqueue(new Callback<List<Recipe>>(){
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipeList = response.body();
                recipes.setValue(recipeList);
                Log.d("BBBB", "load data from remote sercer and set data in mutalble livedata");
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }

    /**
     * expose recipes to its consumers
     * @return
     */
    public MutableLiveData<List<Recipe>> getRecipes(){
        return recipes;
    }
}
