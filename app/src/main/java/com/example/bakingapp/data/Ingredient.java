package com.example.bakingapp.data;

import com.squareup.moshi.Json;

public class Ingredient {

    @Json(name = "quantity")
    private Double quantity;
    @Json(name = "measure")
    private String measure;
    @Json(name = "ingredient")
    private String ingredient;

    /**
     * No args constructor for use in serialization
     */
    public Ingredient() {
    }

    /**
     * @param quantity
     * @param measure
     * @param ingredient
     */
    public Ingredient(Double quantity, String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}