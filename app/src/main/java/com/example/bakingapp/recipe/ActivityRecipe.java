package com.example.bakingapp.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;
import com.example.bakingapp.step.ActivityStep;
import com.example.bakingapp.step.FragmentStep;

public class ActivityRecipe extends AppCompatActivity implements AdapterStep.ListItemClickeListener {

    public static final String EXTRA_INT_STEP = "from_recipe_to_step_step_position";
    public static final String EXTRA_INT_RECIPE = "from_recipe_to_step_recipe_position";

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (findViewById(R.id.fragment_host) != null) {
            mTwoPane = true;

            FragmentManager manager = getSupportFragmentManager();
            FragmentStep fragmentStep = new FragmentStep();
            fragmentStep.setPosition(0,0);
            manager.beginTransaction()
                    .add(R.id.fragment_host, fragmentStep)
                    .commit();
        } else {
            mTwoPane = false;
        }

    }

    @Override
    public void onListItemClicked(int position, int recipePosition) {

        if (mTwoPane == false) {
            Log.d("onListItemClicked", "one");
            Intent intent = new Intent(this, ActivityStep.class);
            intent.putExtra(EXTRA_INT_STEP, position);
            intent.putExtra(EXTRA_INT_RECIPE, recipePosition);
            startActivity(intent);
        } else {
            Log.d("onListItemClicked", "two");

            FragmentManager manager = getSupportFragmentManager();
            FragmentStep fragmentStep = new FragmentStep();
            fragmentStep.setPosition(position,recipePosition);
            manager.beginTransaction()
                    .replace(R.id.fragment_host, fragmentStep)
                    .commit();
        }
    }
}
