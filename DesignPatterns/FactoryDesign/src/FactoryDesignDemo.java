public class FactoryDesignDemo {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle car = vehicleFactory.createVehicle("car");
        car.drive();

        Vehicle bike = vehicleFactory.createVehicle("motorbike");
        bike.drive();
    }


}
