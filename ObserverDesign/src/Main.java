public class Main {
    public static void main(String[] args) {

        SubjectImpl subject = new SubjectImpl("Sunny");
        Observer observer1 = new PhoneObserverImpl();
        Observer observer2 = new TvObserverImpl();

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.notifyObservers();

        subject.setWeather("Rainy");

    }
}