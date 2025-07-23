package BookMyShowLLD.models.movies;

import java.util.HashMap;
import java.util.Map;

public class MovieFactory {

    private static final Map<String,Movie> movieCache = new HashMap<>();

    public static Movie createMovie(int id, String name,int duration){
        return movieCache.computeIfAbsent(name,key-> new Movie(id,name,duration));
    }

}
