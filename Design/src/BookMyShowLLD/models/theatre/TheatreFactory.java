package BookMyShowLLD.models.theatre;

import BookMyShowLLD.enums.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheatreFactory {

    public static Theatre createTheatre(int theatreId, String name, City city, List<Show> show,List<Screen> screens){
        Theatre theatre = new Theatre();
        theatre.setTheatreName(name);
        theatre.setTheatreId(theatreId);
        theatre.setCity(city);
        theatre.setShow(show);
        theatre.setScreen(screens);
        return theatre;
    }


}
