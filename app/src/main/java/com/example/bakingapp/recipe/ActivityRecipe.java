package com.example.bakingapp.recipe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.bakingapp.R;
import com.example.bakingapp.data.Step;
import com.example.bakingapp.step.ActivityStep;
import com.example.bakingapp.step.FragmentStep;
import com.example.bakingapp.ui.ActivityMain;

public class ActivityRecipe extends AppCompatActivity implements AdapterStep.ListItemClickeListener, FragmentStep.ButtonClickListener {

    public static final String EXTRA_INT_STEP = "from_recipe_to_step_step_position";
    public static final String EXTRA_INT_RECIPE = "from_recipe_to_step_recipe_position";

    private int positionRecipe;
    private int positionStep;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

//        getPreference();
        Intent intent = getIntent();
        positionRecipe = intent.getIntExtra(ActivityMain.EXTRA_INT_RECIPE, 0);
        positionStep = 0;

        if (findViewById(R.id.fragment_host) != null) {
            mTwoPane = true;

            FragmentManager manager = getSupportFragmentManager();
            FragmentStep fragmentStep = new FragmentStep();
            fragmentStep.setPosition(positionStep, positionRecipe, this);
            manager.beginTransaction()
                    .add(R.id.fragment_host, fragmentStep)
                    .commit();
        } else {
            mTwoPane = false;
        }

    }

    @Override
    public void onListItemClicked(int position, int recipePosition) {
//        savePreference(recipePosition, position);

        if (mTwoPane == false) {
            Intent intent = new Intent(this, ActivityStep.class);
            intent.putExtra(EXTRA_INT_STEP, position);
            intent.putExtra(EXTRA_INT_RECIPE, recipePosition);
            startActivity(intent);
        } else {
            FragmentManager manager = getSupportFragmentManager();
            FragmentStep fragmentStep = new FragmentStep();
            positionStep = position;
            fragmentStep.setPosition(positionStep, positionRecipe, this);
            manager.beginTransaction()
                    .replace(R.id.fragment_host, fragmentStep)
                    .commit();
        }
    }

    @Override
    public void onPreviousClick(int currentPosition) {
        positionStep = currentPosition - 1;
//        savePreference(positionRecipe, positionStep);

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep, positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }

    @Override
    public void onNextClick(int currentPosition) {
        positionStep = currentPosition + 1;
//        savePreference(positionRecipe, positionStep);

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep, positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }

//    private void savePreference(int positionRecipe, int positionStep) {
//        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(EXTRA_INT_RECIPE, positionRecipe);
//        editor.putInt(EXTRA_INT_STEP, positionStep);
//        editor.commit();
//    }
//
//    private void getPreference() {
//        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
//        positionRecipe = sharedPref.getInt(EXTRA_INT_RECIPE, 0);
//        positionStep = sharedPref.getInt(EXTRA_INT_STEP, 0);
//        Log.d("sharedPreference", "recipe is: " + positionRecipe);
//    }
}
