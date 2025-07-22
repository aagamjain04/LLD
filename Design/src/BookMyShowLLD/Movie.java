package BookMyShowLLD;

import java.time.Duration;

public class Movie {

    public Movie(int id,String name,Duration duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    private int id;
    private String name;
    private Duration duration;

    // setter and getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
