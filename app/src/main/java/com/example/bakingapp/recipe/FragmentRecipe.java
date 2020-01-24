package com.example.bakingapp.recipe;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.RecipeApplication;
import com.example.bakingapp.data.Recipe;
import com.example.bakingapp.ui.ActivityMain;

import java.util.List;

public class FragmentRecipe extends Fragment {
    private RecyclerView recyclerViewSteps;
    private TextView tvIngredients;
    private int position;
    private Recipe recipe;
    private AdapterStep adapter;

    public FragmentRecipe() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_recipe, container,false);

        Intent intent = getActivity().getIntent();
        position = intent.getIntExtra(ActivityMain.EXTRA_INT_RECIPE,0);
        Log.d("position is", "is default value? value is: " + position);

        tvIngredients = rootView.findViewById(R.id.tv_ingredients);

        recyclerViewSteps = rootView.findViewById(R.id.recycler_step_list);
        recyclerViewSteps.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSteps.setLayoutManager(layoutManager);
        adapter = new AdapterStep((AdapterStep.ListItemClickeListener) getActivity(), position);
        recyclerViewSteps.setAdapter(adapter);



        observeRecipe();

        return rootView;
    }

    private void observeRecipe() {
        ((RecipeApplication) requireContext().getApplicationContext()).getRepository().getRecipes().observe(
                this, new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        recipe = recipes.get(position);
                        tvIngredients.setText(recipe.getName());
                        adapter.updateData(recipes.get(position).getSteps());

                        Log.d("AAAAA", "data of recipe received. Name is:" + recipes.get(0).getName()
                                +". Image is:"
                                +recipes.get(0).getImage()
                        );
                    }
                }
        );
    }
}
