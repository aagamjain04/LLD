public class TvObserverImpl implements Observer{
    private String weather;
    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    public void display(){
        System.out.println("Tv Display Weather Updated - "+weather);
    }
}
