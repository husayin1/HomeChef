package com.example.foodfusion.views.home.home.view;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodfusion.R;
import com.example.foodfusion.localDataSource.MealLocalDataSource;
import com.example.foodfusion.model.repositories.meal_models.PojoCategory;
import com.example.foodfusion.model.repositories.meal_models.PojoIngredient;
import com.example.foodfusion.model.repositories.meal_models.PojoMeal;
import com.example.foodfusion.remoteDataSource.API.MealsRemoteDataSource;
import com.example.foodfusion.views.home.home.presenter.HomePresenter;
import com.example.foodfusion.views.home.home.presenter.HomePresenterInterface;
import com.example.foodfusion.views.home.home.view.HomeFragmentDirections.*;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView, OnClickListener {
    private static final String TAG = "Home Fragment";
    HomePresenterInterface homePresenter;
    CardView cardView;
    ImageView imgView;
    TextView meal_name;
    TextView meal_description;
    RecyclerView recyclerView;
    private HomeAdapter adapter;
    ConstraintLayout constraintLayoutFragmentHome;
    ImageView imageViewRandomMealFavorite;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycelrViewTrendingMeals);
        cardView = view.findViewById(R.id.cardView);
        imgView = view.findViewById(R.id.imgView);
        meal_name = view.findViewById(R.id.textViewMealName);
        meal_description = view.findViewById(R.id.meal_description);
        imageViewRandomMealFavorite = view.findViewById(R.id.imageViewRandomMealFavorite);

        constraintLayoutFragmentHome = view.findViewById(R.id.constraintLayoutFragmentHome);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new HomeAdapter(this.getContext(), new ArrayList<>(), this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        homePresenter = new HomePresenter(this, MealsRemoteDataSource.getInstance(), MealLocalDataSource.getInstance(getContext()));
        homePresenter.getRandomMeals();
        homePresenter.getCategories();
        homePresenter.getIngredients();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        imageViewRandomMealFavorite.setImageResource(R.drawable.favoritefill);
    }

    @Override
    public void showRandomData(List<PojoMeal> meals) {
        Log.i(TAG, "showData: " + meals);
        Glide.with(this.getContext())
                .load(meals.get(0).strMealThumb)
                .centerCrop()
                .placeholder(R.drawable.molokhia)
                .into(imgView);
        meal_name.setText(meals.get(0).strMeal);
        meal_description.setText(meals.get(0).strArea);
        imageViewRandomMealFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePresenter.addToFav(meals.get(0));
                imageViewRandomMealFavorite.setImageResource(R.drawable.saveicon);
                Toast.makeText(getContext(), "Add " + meals.get(0).getStrMeal() + " to favorite", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this.getContext(), "Got Random Data", Toast.LENGTH_SHORT).show();
        constraintLayoutFragmentHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionHomeFragment2ToMealDetailsFragment2 action = HomeFragmentDirections.actionHomeFragment2ToMealDetailsFragment2(meals.get(0));
                Navigation.findNavController(view).navigate(action);
                Log.i(TAG, "onClick: we clicked on the card ");
            }
        });
    }

    @Override
    public void showRandomErrorMsg(String error) {
        Log.i(TAG, "showErrorMsg: " + error);
        Toast.makeText(this.getContext(), "Failed to get Random", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCategoriesData(List<PojoCategory> categories) {
        Log.i(TAG, "showCategoriesData: " + categories);
        adapter.setMeals(categories);
        adapter.notifyDataSetChanged();
        Toast.makeText(this.getContext(), "Got Categories", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCategoriesErrorMsg(String error) {
        Log.i(TAG, "showCategoriesErrorMsg: " + error);
        Toast.makeText(this.getContext(), "failed to get categories", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIngredientsData(List<PojoIngredient> ingredients) {
        Log.i(TAG, "showIngredientsData: " + ingredients);
        Toast.makeText(this.getContext(), "GotTrendingMeals", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIngredientsErrorMsg(String error) {
        Toast.makeText(this.getContext(), "failed to Ingredients", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(PojoCategory meal) {
        Toast.makeText(getContext(), meal.strCategory, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onClick:FragmentHome clicked on " + meal.idCategory);
    }
}