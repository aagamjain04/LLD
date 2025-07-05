package CarRentalSystemLLD.Product;

public class Car extends Vehicle{
    public Car(String vehicleId, VehicleType vehicleType, String brand, String model, int year, double rentalPricePerDay) {
        super(vehicleId, vehicleType, brand, model, year, rentalPricePerDay);
    }
}
