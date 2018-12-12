package com.example.heronymousbot.popularmovies;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class QueryUtils {
    public static ArrayList<Movie> fetchMovieData(String requestUrl) {
        //Create URL object
        URL url = createUrl(requestUrl);



        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e("QueryUtils: ", "Problem making the HTTP request.", e);
        }
        //Extract relevant fields from the JSON response and create a list of obamaNews
        ArrayList<Movie> moviesList = extractFeatureFromJson(jsonResponse);

        return moviesList;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("Query Utils: ", "Problem building the URL ", e);
        }
        return url;
    }

    private static ArrayList<Movie> extractFeatureFromJson(String movieJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Movie> moviesList = new ArrayList<>();

        //Try to parse the JSOn response string. If there's a problem with the
        //way the JSON is formatted, a JSONException object will be throw.
        //Catch the exception so the app doesn't crash and print the error message
        //to the logs.

        try {
            //Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(movieJSON);



            JSONArray movieArray = baseJsonResponse.getJSONArray("results");

            //For each piece of news, in the obamaArray, create an Obama object

            int i;
            for (i = 0; i < movieArray.length(); i++) {

                //Get a singe obamaNews at position i within the list of obamaNews

                JSONObject currentMovie = movieArray.getJSONObject(i);

                // For a given piece of News, extract the JSONobject associated with the
                //key called "results"


                //Extract the value for the key called "type"

                String title = currentMovie.getString("title");

                //Extract the value for the key called "sectionName"

                double voteAverage = currentMovie.getDouble("vote_average");

                //Extract the value for the key called "webTitle"

                String posterPath = currentMovie.getString("poster_path");

                //Extract the value for the key called "webURL"

                String releaseDate = currentMovie.getString("release_date");

                //Extract the value of the key called "webPublicationDate"

                String overview = currentMovie.getString("overview");

                //Create a new Obama object with the variables extracted from the URL

                Movie mMovie = new Movie(title, releaseDate, posterPath, voteAverage, overview);

                moviesList.add(mMovie);

            }

        } catch (JSONException e) {
            e.printStackTrace();

            //if an error is thrown when executing any of the above statements in the "try" block
            //catch the exception here, so the app doesn't crash.

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return moviesList;
    }


}
