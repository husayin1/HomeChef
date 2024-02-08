package com.example.foodfusion.features.home.mealPlan.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodfusion.features.home.mealPlan.view.MealPlanView;
import com.example.foodfusion.localDataSource.MealLocalDataSourceInterface;
import com.example.foodfusion.model.repositories.local_repo.FavAndPlannerInterface;
import com.example.foodfusion.model.repositories.meal_models.pojos.PojoPlanner;

import java.util.List;

public class MealPlanPresenter implements MealPlanPresenterInterface{

    private FavAndPlannerInterface _repo;
    MealPlanView _view;

    public MealPlanPresenter(MealPlanView _view,FavAndPlannerInterface _repo){
        this._repo=_repo;
        this._view=_view;
    }



    @Override
    public void getPlannedMealsByDate(String date) {
        Log.i("TAG", "getPlannedMealsByDate: from presneter" + date);
        _repo.getAllMealsFromPlannerAtDate(date).observe((LifecycleOwner) _view, new Observer<List<PojoPlanner>>() {
            @Override
            public void onChanged(List<PojoPlanner> planners) {
                _view.showData(planners);
            }
        });
    }

    @Override
    public void removeItemFromPlanner(PojoPlanner meal) {
        _repo.deleteMealFromPlanner(meal);
    }
}
