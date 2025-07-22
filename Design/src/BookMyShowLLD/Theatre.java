package BookMyShowLLD;

import java.util.List;

public class Theatre {
    int id;
    Location location;
    List<Screen> listOfScreens;
    List<Show> listOfShows;

    // constructor

    public Theatre(int id, Location location, List<Screen> listOfScreens, List<Show> listOfShows) {
        this.id = id;
        this.location = location;
        this.listOfScreens = listOfScreens;
        this.listOfShows = listOfShows;
    }

    //getter and setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Screen> getListOfScreens() {
        return listOfScreens;
    }

    public void setListOfScreens(List<Screen> listOfScreens) {
        this.listOfScreens = listOfScreens;
    }

    public List<Show> getListOfShows() {
        return listOfShows;
    }

    public void setListOfShows(List<Show> listOfShows) {
        this.listOfShows = listOfShows;
    }
}
