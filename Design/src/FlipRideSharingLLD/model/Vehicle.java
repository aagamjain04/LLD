package FlipRideSharingLLD.model;

public class Vehicle {
    String ownerName;
    String model;
    String registrationNo;
    boolean hasActiveRide;

    public Vehicle(String ownerName, String model, String registrationNo) {
        this.ownerName = ownerName;
        this.model = model;
        this.registrationNo = registrationNo;
        this.hasActiveRide = false;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public boolean isHasActiveRide() {
        return hasActiveRide;
    }

    public void setHasActiveRide(boolean status) {
        this.hasActiveRide = status;
    }
}
