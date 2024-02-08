package com.example.foodfusion.features.home.search.view;

import com.example.foodfusion.model.repositories.meal_models.pojos.PojoArea;
import com.example.foodfusion.model.repositories.meal_models.pojos.PojoCategory;
import com.example.foodfusion.model.repositories.meal_models.pojos.PojoIngredient;
import com.example.foodfusion.model.repositories.meal_models.pojos.PojoMeal;
import com.example.foodfusion.model.repositories.meal_models.root_pojos.RootArea;
import com.example.foodfusion.model.repositories.meal_models.root_pojos.RootCategory;
import com.example.foodfusion.model.repositories.meal_models.root_pojos.RootIngredient;
import com.example.foodfusion.model.repositories.meal_models.root_pojos.RootMainMeal;
import com.example.foodfusion.model.repositories.meal_models.root_pojos.RootMeal;

import java.util.ArrayList;
import java.util.List;

public interface SearchView {

    void showIngredientsData(RootIngredient ingredients);
    void showCategoriesData(RootCategory categories);
    void showCountriesData(RootArea areas);
    void showSearchedMealData(ArrayList<PojoMeal> meals);
    void showSearchResultData(RootMainMeal mainMeal);

}
