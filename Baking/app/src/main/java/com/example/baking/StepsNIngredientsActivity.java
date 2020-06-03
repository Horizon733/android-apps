package com.example.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.baking.adapters.CategoryAdapter;
import com.example.baking.models.Ingredients;
import com.example.baking.models.Recipe;
import com.example.baking.models.Steps;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class StepsNIngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_n_ingredients);
       Recipe recipe = (Recipe) getIntent().getSerializableExtra("ingredients");
        Log.e("steps",""+recipe.getIngredients().length);
        //CategoryAdapter categoryAdapter =new CategoryAdapter(this,getSupportFragmentManager(),steps,ingredients);
        //ViewPager viewPager = findViewById(R.id.viewpager);
        //viewPager.setAdapter(categoryAdapter);
        //TabLayout tabLayout =  findViewById(R.id.tab_layout);
        //tabLayout.setupWithViewPager(viewPager);
    }
}
