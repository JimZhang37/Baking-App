package com.example.bakingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeApplication;
import com.example.bakingapp.data.Recipe;
import com.example.bakingapp.recipe.ActivityRecipe;

import java.util.List;

public class ActivityMain extends AppCompatActivity implements AdapterRecipe.ListItemClickeListener {

    private RecyclerView recyclerViewRecipeList;
    private AdapterRecipe adapter;
    public static final String EXTRA_INT_RECIPE = "from_main_to_recipe";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewRecipeList = findViewById(R.id.recycler_recipe_list);
        adapter = new AdapterRecipe(this);
        recyclerViewRecipeList.setAdapter(adapter);
        recyclerViewRecipeList.setHasFixedSize(true);
        int num = calculateNoOfColumns(getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), num);
        recyclerViewRecipeList.setLayoutManager(layoutManager);

        observeRecipe();
    }

    private void observeRecipe() {
        ((RecipeApplication) getApplication()).getRepository().getRecipes().observe(
                this, new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        adapter.updateData(recipes);
                        Log.d("onChanged", "ActivityMain");
                    }
                }
        );
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if (noOfColumns < 3) {
            noOfColumns = 1;
        } else {
            noOfColumns = 3;
        }
        return noOfColumns;
    }

    @Override
    public void onListItemClicked(int position) {
        Intent intent = new Intent(this, ActivityRecipe.class);
        intent.putExtra(EXTRA_INT_RECIPE, position);
        startActivity(intent);
    }
}
