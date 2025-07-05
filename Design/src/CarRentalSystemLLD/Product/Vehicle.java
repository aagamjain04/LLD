package CarRentalSystemLLD.Product;

public class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;
    private String brand;
    private String model;
    private int year;
    private double rentalPricePerDay;
    private Status vehicleStatus;

    public Vehicle(String vehicleId, VehicleType vehicleType, String brand, String model, int year, double rentalPricePerDay) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalPricePerDay = rentalPricePerDay;
        this.vehicleStatus = Status.ACTIVE;
    }

    // Getters and Setters
    public String getVehicleId() {
        return vehicleId;
    }
    public VehicleType getType() {
        return vehicleType;
    }

    public Status getStatus() {
        return vehicleStatus;
    }

    public void setStatus(Status status) {
        this.vehicleStatus = status;
    }

    public double getRentalPricePerDay(){
        return rentalPricePerDay;
    }

}
