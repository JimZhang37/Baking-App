package com.example.bakingapp.data;
import java.util.List;
import com.squareup.moshi.Json;

public class Recipe {

    @Json(name = "id")
    private Integer id;
    @Json(name = "name")
    private String name;
    @Json(name = "ingredients")
    private List<Ingredient> ingredients = null;
    @Json(name = "steps")
    private List<Step> steps = null;
    @Json(name = "servings")
    private Integer servings;
    @Json(name = "image")
    private String image;

    /**
     * No args constructor for use in serialization
     *
     */
    public Recipe() {
    }

    /**
     *
     * @param image
     * @param servings
     * @param name
     * @param ingredients
     * @param id
     * @param steps
     */
    public Recipe(Integer id, String name, List<Ingredient> ingredients, List<Step> steps, Integer servings, String image) {
        super();
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}