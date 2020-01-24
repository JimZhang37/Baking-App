package com.example.bakingapp.step;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeApplication;
import com.example.bakingapp.data.Recipe;
import com.example.bakingapp.data.Step;
import com.example.bakingapp.recipe.ActivityRecipe;

import java.util.List;

public class FragmentStep extends Fragment {
    private int positionStep;
    private int positionRecipe;
    private Step step;
    private TextView tvStepDestription;

    public FragmentStep() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        Intent intent = getActivity().getIntent();
        positionStep = intent.getIntExtra(ActivityRecipe.EXTRA_INT_STEP, 0);
        positionRecipe = intent.getIntExtra(ActivityRecipe.EXTRA_INT_RECIPE, 0);
        observeStep();
        tvStepDestription = rootView.findViewById(R.id.tv_step_description);

        return rootView;
    }

    private void observeStep() {
        ((RecipeApplication) requireContext().getApplicationContext()).getRepository().getRecipes().observe(
                this, new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        step = recipes.get(positionRecipe).getSteps().get(positionStep);
                        tvStepDestription.setText(step.getDescription());

                        Log.d("AAAAA", "data of recipe received. Name is:" + recipes.get(0).getName()
                                + ". Image is:"
                                + recipes.get(0).getImage()
                        );
                    }
                }
        );
    }
}
