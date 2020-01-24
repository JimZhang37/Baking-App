package com.example.bakingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeApplication;
import com.example.bakingapp.data.Recipe;

import java.util.List;

public class ActivityMain extends AppCompatActivity {
    private RecyclerView recyclerViewRecipeList;
    private TextView tvHelloworld;
    private List<Recipe> recipeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewRecipeList = findViewById(R.id.recycler_recipe_list);
        tvHelloworld = findViewById(R.id.tv_helloworld);

        observeRecipe();
    }

    private void observeRecipe(){
        ((RecipeApplication )getApplication()).getRepository().getRecipes().observe(
                this, new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        recipeList = recipes;
                        Log.d("AAAAA", "data of recipe received. num is:" + recipes.size());
                        tvHelloworld.setText("num is: " + recipes.size() + recipes.get(0).getName());
                    }
                }
        );
    }
}
