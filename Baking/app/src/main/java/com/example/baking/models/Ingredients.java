package com.example.baking.models;

public class Ingredients {
    private double quantity;
    private String measure;
    private String ingredients;

    public Ingredients(double quantity, String measure, String ingredients){
        this.ingredients = ingredients;
        this.quantity = quantity;
        this.measure = measure;
    }
    public double getQuantity(){ return this.quantity; }
    public String getMeasure(){ return this.measure; }
    public String getIngredients(){ return this.ingredients; }
}
