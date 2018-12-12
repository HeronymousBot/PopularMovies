package com.example.heronymousbot.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView moviePoster = findViewById(R.id.movie_poster);
        TextView movieTitle = findViewById(R.id.movie_title);
        TextView movieVoteAverage = findViewById(R.id.movie_vote_average);
        TextView movieOverview = findViewById(R.id.movie_overview);
        TextView movieReleaseDate = findViewById(R.id.movie_release_date);

        Intent intent = getIntent();

        final String title = intent.getStringExtra(MovieAdapter.KEY_TITLE);
        String poster = intent.getStringExtra(MovieAdapter.KEY_POSTER);
        final String overview = intent.getStringExtra(MovieAdapter.KEY_OVERVIEW);
        final String releaseDate = intent.getStringExtra(MovieAdapter.KEY_RELEASE_DATE);
        String voteAverage = intent.getStringExtra(MovieAdapter.KEY_VOTE_AVERAGE);

        String modifiedDate;
        if (releaseDate != null) {
            modifiedDate = releaseDate.substring(8, 10) + "/" +
                    releaseDate.substring(5, 7) + "/" + releaseDate.substring(0, 4);

            modifiedDate = "Released in " + modifiedDate;
        } else {
            modifiedDate = "No release date available.";
        }


        movieTitle.setText(title);
        movieOverview.setText(overview);
        movieReleaseDate.setText(modifiedDate);
        movieVoteAverage.setText(voteAverage);


        Picasso.get().load(poster).fit().into(moviePoster);
    }
}
