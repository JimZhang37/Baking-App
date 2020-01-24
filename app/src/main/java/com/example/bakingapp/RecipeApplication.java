package com.example.bakingapp;

import android.app.Application;
import android.util.Log;

import com.example.bakingapp.data.DefaultRepository;

public class RecipeApplication extends Application {
    private DefaultRepository repository;

    public RecipeApplication() {
        this.repository = new DefaultRepository();
    }

    public DefaultRepository getRepository() {
        Log.d("RecipeApplication", "getRepository");
        if(repository == null){
            repository = new DefaultRepository();
        }
        return repository;
    }
}
