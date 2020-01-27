package com.example.bakingapp.step;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bakingapp.R;
import com.example.bakingapp.recipe.ActivityRecipe;
import com.example.bakingapp.ui.ActivityMain;

public class ActivityStep extends AppCompatActivity implements FragmentStep.ButtonClickListener {
    private int positionStep;
    private int positionRecipe;
    FragmentManager manager;
    FragmentStep fragmentStep;

    /**
     * Define Up button in action bar's behavior, navigating to ActivityRecipe with the same Recipe.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, ActivityRecipe.class);
        intent.putExtra(ActivityMain.EXTRA_INT_RECIPE, positionRecipe);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        positionStep = intent.getIntExtra(ActivityRecipe.EXTRA_INT_STEP, 0);
        positionRecipe = intent.getIntExtra(ActivityRecipe.EXTRA_INT_RECIPE, 0);

        manager = getSupportFragmentManager();
        Fragment a =  manager.findFragmentByTag("FRAGMENT");
        if(a == null) {
            fragmentStep = new FragmentStep();
            fragmentStep.setPosition(positionStep, positionRecipe, this);
            manager.beginTransaction()
                    .add(R.id.fragment_host, fragmentStep, "FRAGMENT")
                    .commit();
        }

//        Log.d("ActivityStep","onCreate, onChanged");
    }

    @Override
    public void onPreviousClick(int currentPosition) {
        positionStep = currentPosition - 1;
        manager = getSupportFragmentManager();
        fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep, positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }

    @Override
    public void onNextClick(int currentPosition) {
        positionStep = currentPosition + 1;
        manager = getSupportFragmentManager();
        fragmentStep = new FragmentStep();
        fragmentStep.setPosition(positionStep, positionRecipe, this);
        manager.beginTransaction()
                .replace(R.id.fragment_host, fragmentStep)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        manager.beginTransaction()
//                .remove(fragmentStep)
//                .commit();
        Log.d("onDestroy@ActivityStep", "onChanged fragmentstep removed");
    }
}
