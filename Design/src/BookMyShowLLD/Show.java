package BookMyShowLLD;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    int id;
    Movie movie;
    Screen screen;
    LocalDateTime startTime;
    List<Seat> bookedSeats;

    public Show(int id, Movie movie, Screen screen, LocalDateTime startTime, List<Seat> bookedSeats) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.bookedSeats = bookedSeats;
    }

    //setters and getters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

}
