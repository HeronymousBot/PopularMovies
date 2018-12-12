package com.example.heronymousbot.popularmovies;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mLoadingIndicator;
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        makeMovieSearchQuery("popular");

    }

    private void makeMovieSearchQuery(String movieCategory) {

        URL movieSearchUrl = NetworkUtils.buildUrl(movieCategory);
        new MovieQueryTask().execute(movieSearchUrl);
    }

    public class MovieQueryTask extends AsyncTask<URL, Void, ArrayList<Movie>> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(URL... params) {
            URL searchUrl = params[0];
            ArrayList<Movie> moviesList = null;
            try {
                moviesList = QueryUtils.fetchMovieData(searchUrl.toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return moviesList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> moviesList) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);
            mRecyclerView = findViewById(R.id.rv_movie_list);
            mRecyclerView.setHasFixedSize(true);
            mAdapter = new MovieAdapter(getApplicationContext(), moviesList);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        switch (itemThatWasClickedId) {

            case R.id.most_popular_option:
                Toast.makeText(this, "These are the most popular movies!",
                        Toast.LENGTH_SHORT).show();
                makeMovieSearchQuery("popular");
                return true;

            case R.id.top_rated_option:
                Toast.makeText(this, "These are the top rated movies!",
                        Toast.LENGTH_SHORT).show();
                makeMovieSearchQuery("top_rated");
                return true;

            case R.id.upcoming_option:
                Toast.makeText(this, "These are the upcoming movies!",
                        Toast.LENGTH_SHORT).show();
                makeMovieSearchQuery("upcoming");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

}
