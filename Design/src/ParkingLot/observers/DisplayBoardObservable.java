package ParkingLot.observers;

public interface DisplayBoardObservable {
    void addObserver(DisplayBoard observer);
    void removeObserver(DisplayBoard observer);
    void notifyObservers();
}
