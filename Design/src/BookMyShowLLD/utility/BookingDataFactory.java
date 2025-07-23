package BookMyShowLLD.utility;

import BookMyShowLLD.controller.MovieController;
import BookMyShowLLD.controller.TheatreController;
import BookMyShowLLD.enums.City;
import BookMyShowLLD.models.movies.Movie;
import BookMyShowLLD.models.theatre.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingDataFactory {

    public static Screen createScreen(int id) {

        Screen screen = new Screen();
        screen.setScreenID(id);
        screen.setSeats(createSeats());

        return screen;
    }

    public static Show createShow(int showId, Movie movie, int showStartTime) {
        Show show = new Show();
        show.setShowId(showId);
        show.setMovie(movie);
        show.setShowStartTime(showStartTime);
        return show;
    }

    public static List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seats.add(seat);
        }
        return seats;
    }

    public static List<Movie> createMovies(MovieController movieController){
        Movie barbie = new Movie(1,"BARBIE",128);
        Movie oppenheimer = new Movie(2,"OPPENHEIMER",180);

        movieController.addMovie(City.Bangalore,barbie);
        movieController.addMovie(City.Delhi,barbie);
        movieController.addMovie(City.Delhi,oppenheimer);
        movieController.addMovie(City.Bangalore,oppenheimer);

        return List.of(oppenheimer,barbie);
    }

    public static void createTheatres(MovieController movieController, TheatreController theatreController){


        Movie barbie = movieController.getMovieByName("BARBIE");
        Movie oppenheimer = movieController.getMovieByName("OPPENHEIMER");

        Theatre inox = TheatreFactory.createTheatre(
                1,
                "INOX",
                City.Bangalore,
                List.of(createShow(1,barbie,10),createShow(2,oppenheimer,20)),
                List.of(createScreen(1),createScreen(2))
        );

        Theatre pvr = TheatreFactory.createTheatre(
                2,
                "PVR",
                City.Delhi,
                List.of(createShow(3,barbie,10),createShow(4,oppenheimer,20)),
                List.of(createScreen(3),createScreen(4))
        );

        theatreController.addTheatre(City.Bangalore,inox);
        theatreController.addTheatre(City.Delhi,pvr);
    }

}
