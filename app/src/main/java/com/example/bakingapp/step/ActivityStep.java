package com.example.bakingapp.step;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.bakingapp.R;
import com.example.bakingapp.recipe.ActivityRecipe;

public class ActivityStep extends AppCompatActivity {
    private int positionStep;
    private int positionRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        Intent intent = getIntent();
        positionStep = intent.getIntExtra(ActivityRecipe.EXTRA_INT_STEP, 0);
        positionRecipe = intent.getIntExtra(ActivityRecipe.EXTRA_INT_RECIPE, 0);

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep,positionRecipe);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }
}
