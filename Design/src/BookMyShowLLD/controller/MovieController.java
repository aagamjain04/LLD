package BookMyShowLLD.controller;

import BookMyShowLLD.enums.City;
import BookMyShowLLD.models.movies.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {

    List<Movie> allMovies;
    Map<City,List<Movie>> cityVsMovies;

    public MovieController(){
        allMovies = new ArrayList<>();
        cityVsMovies = new HashMap<>();
    }

    //ADD movie to a particular city
    public void addMovie(City city,Movie movie){

        allMovies.add(movie);

        cityVsMovies.computeIfAbsent(city, k-> new ArrayList<>()).add(movie);
    }

    public Movie getMovieByName(String movieName){

        return allMovies.stream().filter(movie -> movieName.equals(movie.getMovieName())).findFirst().orElse(null);
    }

    public List<Movie> getMoviesByCity(City city){
        return cityVsMovies.get(city);
    }

    //REMOVE movie from a particular city, make use of cityVsMovies map

    //UPDATE movie of a particular city, make use of cityVsMovies map

    //CRUD operation based on Movie ID, make use of allMovies list

}
