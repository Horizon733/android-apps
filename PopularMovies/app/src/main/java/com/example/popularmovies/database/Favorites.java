package com.example.popularmovies.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorites {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String movieName;
    private int movieId;
@Ignore
    public Favorites(String movieName,int movieId){
        this.movieName = movieName;
        this.movieId = movieId;
    }

    public Favorites(int id, String movieName,int movieId){
        this.id = id;
        this.movieName = movieName;
        this.movieId = movieId;
    }

    public String getMovieName(){return movieName;}
    public int getMovieId(){return movieId;}
    public int getId(){return id;}
}
