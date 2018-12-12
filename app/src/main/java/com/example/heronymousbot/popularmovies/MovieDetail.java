package com.example.heronymousbot.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        switch (itemThatWasClickedId) {

            case R.id.back_to_main:
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
