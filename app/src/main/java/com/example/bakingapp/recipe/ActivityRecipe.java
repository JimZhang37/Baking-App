package com.example.bakingapp.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bakingapp.R;
import com.example.bakingapp.step.ActivityStep;

public class ActivityRecipe extends AppCompatActivity implements AdapterStep.ListItemClickeListener {

    public static final String EXTRA_INT_STEP = "from_recipe_to_step_step_position";
    public static final String EXTRA_INT_RECIPE = "from_recipe_to_step_recipe_position";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


    }

    @Override
    public void onListItemClicked(int position, int recipePosition) {
        Intent intent = new Intent(this, ActivityStep.class);
        intent.putExtra(EXTRA_INT_STEP, position);
        intent.putExtra(EXTRA_INT_RECIPE, recipePosition);
        startActivity(intent);
    }
}
