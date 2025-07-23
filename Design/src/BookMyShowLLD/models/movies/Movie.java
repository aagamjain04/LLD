package BookMyShowLLD.models.movies;

public class Movie {
    int movieId;
    String movieName;
    int movieDurationInMinutes;

    public Movie(int movieId, String movieName, int movieDurationInMinutes) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    // getters and setters


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieDurationInMinutes() {
        return movieDurationInMinutes;
    }

    public void setMovieDurationInMinutes(int movieDurationInMinutes) {
        this.movieDurationInMinutes = movieDurationInMinutes;
    }
}
