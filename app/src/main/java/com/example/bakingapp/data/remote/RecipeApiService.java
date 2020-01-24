package com.example.bakingapp.data.remote;

import com.example.bakingapp.data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RecipeApiService {


    //    String PATH_Recipe = "android-baking-app-json";
    String PATH_Recipe = "baking.json";

    @GET(PATH_Recipe)
    Call<List<Recipe>> getRecipesAsync();
}
