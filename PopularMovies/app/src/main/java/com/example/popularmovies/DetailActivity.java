package com.example.popularmovies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.popularmovies.database.AppDatabase;
import com.example.popularmovies.database.Favorites;
import com.example.popularmovies.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final int DEFAULT_TASK_ID = -1;
    private int mfavoriteId = DEFAULT_TASK_ID;
    private boolean isButtonClicked = false;
    private AppDatabase mDb;
    private Favorites favorites;

    private  String poster,posterBD,date,overview,ratings;
    String videosLink = "https://api.themoviedb.org/3/movie/";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDb = AppDatabase.getInstance(getApplicationContext());
        data();

        final int movieId =  getIntent().getIntExtra("movieId",DEFAULT_TASK_ID);
        videosLink += movieId;
        String name = getIntent().getStringExtra("movieName");

        final TextView movieName = findViewById(R.id.movie_name_tv);
        movieName.setText(name);
        TextView moviePlot = findViewById(R.id.movie_plot_tv);
        moviePlot.setText(overview);
        TextView movieRatings = findViewById(R.id.movie_ratings_tv);
        movieRatings.setText(ratings);
        TextView moviedate = findViewById(R.id.movie_date_tv);
            moviedate.setText(date);

        ImageView moviePoster = findViewById(R.id.poster_iv);
        Picasso.with(this)
                .load(poster)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(moviePoster);
        ImageView moviePosterBD = findViewById(R.id.poster_back_drop_iv);
        Picasso.with(this)
                .load(posterBD)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(moviePosterBD);
         favorites = new Favorites(name,movieId);
        final ToggleButton bookmark = findViewById(R.id.bookmark_movie);
        bookmark.setChecked(false);
        bookmark.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_border_white_24dp));
        bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (isChecked ) {
                        bookmark.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_red_24dp));
                        Toast.makeText(DetailActivity.this, "" + movieName.getText(), Toast.LENGTH_SHORT).show();
                        Log.v("Movie Id",""+movieId);
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                if (mfavoriteId == DEFAULT_TASK_ID) {
                                    mDb.movieDAO().insertMovie(favorites);
                                }
                            }
                        });
                    } else{
                        bookmark.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_border_white_24dp));
                        mDb.movieDAO().deleteMovie(favorites);
                    }

            }
        });
        Favorites fav =  mDb.movieDAO().loadTasksById(movieId);
        int id= 0;
        if(fav != null) {
             id = fav.getMovieId();
            if(id == movieId){
                bookmark.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_red_24dp));
                bookmark.setChecked(true);
            }
        }

        if(id == 0) {
            bookmark.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_favorite_border_white_24dp));
            bookmark.setChecked(false);
        }
        new fetchYoutubeLinks();
    }

private void data(){
        poster = getIntent().getStringExtra("moviePoster");
        posterBD = getIntent().getStringExtra("moviePosterBD");
        date = getIntent().getStringExtra("releaseDate");
        overview = getIntent().getStringExtra("overview");
        ratings = getIntent().getStringExtra("ratings");
}

public class fetchYoutubeLinks extends AsyncTask<Void,Void,String>{

    @Override
    protected String doInBackground(Void... voids) {
        videosLink += "/videos";
        URL url = JsonUtils.createUrl(videosLink,null);
        Log.v("Url for videos",""+(url));
        return null;
    }
}


}
