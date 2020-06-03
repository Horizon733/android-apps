package com.example.baking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.baking.adapters.CategoryAdapter;
import com.example.baking.models.Constants;
import com.example.baking.models.Ingredients;
import com.example.baking.models.Recipe;
import com.example.baking.models.Steps;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class StepsNIngredientsActivity extends AppCompatActivity {
    public static boolean mTwoPane;
    static Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_n_ingredients);
        if (findViewById(R.id.steps_details) != null) {
            mTwoPane = true;
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_details,recipeDetailFragment)
                    .commit();

            recipe = (Recipe) getIntent().getSerializableExtra(Constants.RECIPE);
            assert recipe != null;
            Log.e("steps", "" + recipe.getIngredients().length);
            CategoryAdapter categoryAdapter = new CategoryAdapter(this, getSupportFragmentManager()
                    , recipe.getSteps(), recipe.getIngredients());
            ViewPager viewPager = findViewById(R.id.viewpager);
            viewPager.setAdapter(categoryAdapter);
            TabLayout tabLayout = findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager);
            }
        else {
            mTwoPane = false;
            recipe = (Recipe) getIntent().getSerializableExtra(Constants.RECIPE);
            assert recipe != null;
            Log.e("steps", "" + recipe.getIngredients().length);
            CategoryAdapter categoryAdapter = new CategoryAdapter(this, getSupportFragmentManager()
                    , recipe.getSteps(), recipe.getIngredients());
            ViewPager viewPager = findViewById(R.id.viewpager);
            viewPager.setAdapter(categoryAdapter);
            TabLayout tabLayout = findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager);
            }
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(Constants.RECIPE,recipe);
    }
}
