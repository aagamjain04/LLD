package BookMyShowLLD.controller;

import BookMyShowLLD.enums.City;
import BookMyShowLLD.models.movies.Movie;
import BookMyShowLLD.models.theatre.Show;
import BookMyShowLLD.models.theatre.Theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

    List<Theatre> allTheatre;
    Map<City,List<Theatre>> cityVsTheatre;

    public TheatreController(){
        allTheatre = new ArrayList<>();
        cityVsTheatre = new HashMap<>();
    }

    public void addTheatre(City city,Theatre theatre){
        allTheatre.add(theatre);

        cityVsTheatre.computeIfAbsent(city,k->new ArrayList<>()).add(theatre);
    }

    public Map<Theatre,List<Show>> getAllShow(City city, Movie movie){

        // get all theatre of this city
        List<Theatre> theatreList = cityVsTheatre.get(city);
        Map<Theatre,List<Show>> theatreVsShow = new HashMap<>();

        // filter theatre which run given move
        for(Theatre theatre : theatreList){

            // all shows in theatre
            List<Show> shows = theatre.getShow().stream().filter(show -> movie.getMovieId()==show.getMovie().getMovieId()).toList();
            if(!shows.isEmpty()) {
                theatreVsShow.put(theatre, shows);
            }
        }
        return theatreVsShow;
    }






}
