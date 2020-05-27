package com.example.popularmovies.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.R;
import com.example.popularmovies.database.Favorites;
import com.example.popularmovies.model.Constants;
import com.example.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<Movie> mMovieData;
    Context mContext;
    List<Favorites> mFavoritesMovies;
    String mPrefrences;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(int position);
    }
    public MovieAdapter(Context context, MovieAdapterOnClickHandler clickHandler ,List<Movie> movies,String prefrences){
        mContext = context;
        mClickHandler = clickHandler;
        mMovieData = movies;
        mPrefrences = prefrences;

    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.movie_list_item;
        View v = LayoutInflater.from(mContext).inflate(layoutIdForListItem,viewGroup,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if(mPrefrences == Constants.FAVORITES) {
            Favorites currentMovie = mFavoritesMovies.get(position);
            holder.mMoviename.setText(currentMovie.getMovieName());
            Log.e("Favorite Movie Name", "" + currentMovie.getMovieName());
            Picasso.with(mContext)
                    .load(currentMovie.getMoviePoster())
                    .fit()
                    .error(R.drawable.loading)
                    .placeholder(R.drawable.loading)
                    .into(holder.mMoviePoster);
        }
        else if(mPrefrences == null) {
            Movie currentMovie = mMovieData.get(position);
            holder.mMoviename.setText(currentMovie.getMovieName());
            Log.e("Movie Name", "" + currentMovie.getMovieName());
            Picasso.with(mContext)
                    .load(currentMovie.getMoviePoster())
                    .fit()
                    .error(R.drawable.loading)
                    .placeholder(R.drawable.loading)
                    .into(holder.mMoviePoster);
        }
    }

    @Override
    public int getItemCount() {
        if(mMovieData == null ||mMovieData.size() == 0){ return -1;}

        return mMovieData.size();
    }

    public void setMovieData(List<Favorites> movies){
        this.mFavoritesMovies = movies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView mParentlayout;
        public ImageView mMoviePoster;
        public TextView mMoviename;

        public MovieViewHolder(View itemView){
            super(itemView);
            mParentlayout = itemView.findViewById(R.id.movie_cv);
            mMoviename = itemView.findViewById(R.id.movie_name);
            mMoviePoster = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterposition = getAdapterPosition();
            mClickHandler.onClick(adapterposition);
        }
    }

}
