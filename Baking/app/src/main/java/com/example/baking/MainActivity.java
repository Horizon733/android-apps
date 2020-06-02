package com.example.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.baking.adapters.RecipeAdapter;
import com.example.baking.models.Recipe;
import com.example.baking.utils.GetRecipesData;
import com.example.baking.utils.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    List<Recipe> recipeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recipe_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        loadData();
    }
    private void loadData(){
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        GetRecipesData getRecipesData = retrofit.create(GetRecipesData.class);
        Call<List<Recipe>> call = getRecipesData.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipeList = response.body();
                Log.e("List","Count "+recipeList.size());
                mAdapter = new RecipeAdapter(recipeList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
               Log.e("Error", String.valueOf(t.getCause()));
            }
        });
    }

}
