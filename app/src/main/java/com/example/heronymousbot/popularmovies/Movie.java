package com.example.heronymousbot.popularmovies;

public class Movie {
    private String title;
    private String releaseDate;
    private String moviePoster;
    private String overview;
    private String averageVote;
    private int backgroundColor;

    public Movie(String title, String releaseDate, String moviePoster, double averageVote, String overview) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.moviePoster = moviePoster;
        this.averageVote = Double.toString(averageVote);
        this.overview = overview;

        if (averageVote >= 8.0) {
            this.backgroundColor = R.color.freshFilm;
        } else if (averageVote >= 6.0 && averageVote < 8.0) {
            this.backgroundColor = R.color.averageFilm;
        } else {
            this.backgroundColor = R.color.rottenFilm;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMoviePoster() {
        return "https://image.tmdb.org/t/p/w185_and_h278_bestv2" + moviePoster;
    }

    public String getAverageVote() {
        return averageVote;
    }

    public String getOverview() {
        return overview;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
