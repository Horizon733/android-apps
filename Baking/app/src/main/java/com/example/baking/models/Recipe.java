package com.example.baking.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private Ingredients[] ingredients;
    private Steps[] steps;
    private int servings;

    public Recipe(String name,Ingredients[] ingredients, Steps[] steps, int servings){
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    public String getName(){ return this.name;}
    public Ingredients[] getIngredients(){ return this.ingredients;}
    public Steps[] getSteps(){ return this.steps; }
    public int getServings(){ return this.servings;}
}
