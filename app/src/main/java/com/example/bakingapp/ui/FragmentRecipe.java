package com.example.bakingapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;

public class FragmentRecipe extends Fragment {
    private RecyclerView recyclerViewSteps;
    private TextView tvIngredients;
    public FragmentRecipe() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container,false);
        tvIngredients = rootView.findViewById(R.id.tv_ingredients);
        recyclerViewSteps = rootView.findViewById(R.id.recycler_step_list);


        return rootView;
    }
}
