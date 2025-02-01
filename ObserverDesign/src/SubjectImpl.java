import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject{

    List<Observer> observers = new ArrayList<>();
    String weather;

    public SubjectImpl(String weather) {
        this.weather = weather;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(weather);
        }
    }

    public void setWeather(String weather){
        this.weather = weather;
        notifyObservers();
    }

}
