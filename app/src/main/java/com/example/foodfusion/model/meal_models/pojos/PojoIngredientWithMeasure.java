package com.example.foodfusion.model.meal_models.pojos;

public class PojoIngredientWithMeasure {
    String ingredientName;
    String ingredientMeasure;

    public PojoIngredientWithMeasure(String ingredientName, String ingredientMeasure) {
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }
}
