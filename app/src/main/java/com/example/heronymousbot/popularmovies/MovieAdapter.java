package com.example.heronymousbot.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public static final String KEY_TITLE = "title";
    public static final String KEY_POSTER = "poster";
    public static final String KEY_RELEASE_DATE = "releaseDate";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_VOTE_AVERAGE = "voteAverage";
    public static final String KEY_BACKGROUND_COLOR = "backgroundColor";

    private ArrayList<Movie> moviesList;
    private Context context;

    public MovieAdapter(Context context, ArrayList<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Movie currentMovie = moviesList.get(position);
        Picasso.get()
                .load(currentMovie.getMoviePoster()).fit().centerCrop().into(holder.moviePoster);

        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie currentMovie = moviesList.get(position);
                Intent detailIntent = new Intent(v.getContext(), MovieDetail.class);
                detailIntent.putExtra(KEY_TITLE, currentMovie.getTitle());
                detailIntent.putExtra(KEY_POSTER, currentMovie.getMoviePoster());
                detailIntent.putExtra(KEY_RELEASE_DATE, currentMovie.getReleaseDate());
                detailIntent.putExtra(KEY_OVERVIEW, currentMovie.getOverview());
                detailIntent.putExtra(KEY_VOTE_AVERAGE, currentMovie.getAverageVote());
                detailIntent.putExtra(KEY_BACKGROUND_COLOR, currentMovie.getBackgroundColor());
                v.getContext().startActivity(detailIntent);
            }
        });

    }

    @NonNull
    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView moviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_list_item);
        }
    }
}
