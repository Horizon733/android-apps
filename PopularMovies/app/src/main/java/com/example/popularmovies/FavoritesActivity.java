package com.example.popularmovies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.popularmovies.database.AppDatabase;
import com.example.popularmovies.database.Favorites;
import com.example.popularmovies.database.MovieDAO;

import org.w3c.dom.Text;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
TextView mFavMovie;
AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mFavMovie = findViewById(R.id.fav_movie_name);
        mDb = AppDatabase.getInstance(getApplicationContext());
        Favorites fav = mDb.movieDAO().loadTasksById(419704);
        populateUI(fav);
    }


    private void populateUI(Favorites favorite) {
        if(favorite == null){
            mFavMovie.setText("No data");
            return;
        }
        mFavMovie.setText(favorite.getMovieName());

    }

}
