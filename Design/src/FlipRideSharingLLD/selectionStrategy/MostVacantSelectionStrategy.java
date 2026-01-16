package FlipRideSharingLLD.selectionStrategy;



import FlipRideSharingLLD.model.Ride;
import FoodKartLLD.model.Restaurant;

import java.util.Comparator;
import java.util.List;

public class MostVacantSelectionStrategy implements RideSelectionStrategy {

    @Override
    public Ride selectRide(List<Ride> availableRides) {
        return availableRides.stream().max(Comparator.comparingInt(Ride::getAvailableSeats)).orElse(null);
    }
}
