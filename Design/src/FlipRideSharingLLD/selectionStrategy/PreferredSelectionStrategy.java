package FlipRideSharingLLD.selectionStrategy;

import FlipRideSharingLLD.model.Ride;

import java.util.List;

public class PreferredSelectionStrategy implements RideSelectionStrategy {


    String preferredModel;
    public PreferredSelectionStrategy(String model){
        this.preferredModel = model;
    }
    @Override
    public Ride selectRide(List<Ride> availableRides) {
        return availableRides.stream()
                .filter(r -> r.getVehicle().getModel().equalsIgnoreCase(preferredModel))
                .findFirst()
                .orElse(null);
    }
}
