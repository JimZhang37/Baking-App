package com.example.bakingapp.step;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bakingapp.R;
import com.example.bakingapp.recipe.ActivityRecipe;
import com.example.bakingapp.ui.ActivityMain;

public class ActivityStep extends AppCompatActivity implements FragmentStep.ButtonClickListener {
    private int positionStep;
    private int positionRecipe;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, ActivityRecipe.class);
        intent.putExtra(ActivityMain.EXTRA_INT_RECIPE,positionRecipe);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        positionStep = intent.getIntExtra(ActivityRecipe.EXTRA_INT_STEP, 0);
        positionRecipe = intent.getIntExtra(ActivityRecipe.EXTRA_INT_RECIPE, 0);

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep,positionRecipe, this);

        manager.beginTransaction()
                .add(R.id.fragment_host, fragmentStep)
                .commit();
    }

    @Override
    public void onPreviousClick(int currentPosition) {

            positionStep = currentPosition-1;

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep,positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }

    @Override
    public void onNextClick(int currentPosition) {
        positionStep = currentPosition + 1;

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep,positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();

    }
}
