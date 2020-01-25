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
        Intent intent = getIntent();
        positionRecipe = intent.getIntExtra(ActivityMain.EXTRA_INT_RECIPE,0);
        Log.d("Intent recipe", "position is:" + positionRecipe);
        if (findViewById(R.id.fragment_host) != null) {
            mTwoPane = true;

            FragmentManager manager = getSupportFragmentManager();
            FragmentStep fragmentStep = new FragmentStep();

            positionStep = 0;
            fragmentStep.setPosition(positionStep,positionRecipe,this);
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
            positionStep = position;

            fragmentStep.setPosition(positionStep,positionRecipe, this);
            manager.beginTransaction()
                    .replace(R.id.fragment_host, fragmentStep)
                    .commit();
        }
    }

    @Override
    public void onPreviousClick(int currentPosition) {
        positionStep = currentPosition -1;

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();

        fragmentStep.setPosition(positionStep,positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();

    }

    @Override
    public void onNextClick(int currentPosition) {
        positionStep = currentPosition +1;

        FragmentManager manager = getSupportFragmentManager();
        FragmentStep fragmentStep = new FragmentStep();

        fragmentStep.setPosition(positionStep,positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }
}
