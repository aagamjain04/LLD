public class VehicleFactory {
    public Vehicle createVehicle(String type) {
        if (type.equals("car")) {
            return new CarVehicle();
        } else if (type.equals("motorbike")) {
            return new BikeVehicle();
        }
        return null;
    }
}
