package com.example.popularmovies.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorites {

    private String movieName;
    @PrimaryKey
    private int movieId;
    public Favorites(String movieName,int movieId){
        this.movieName = movieName;
        this.movieId = movieId;
    }


    public String getMovieName(){return movieName;}
    public int getMovieId(){return movieId;}
}
