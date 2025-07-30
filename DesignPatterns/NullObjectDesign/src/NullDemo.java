public class NullDemo {
    public static void main(String[] args) {
        System.out.println("Hello");

        Vehiclee car = VehicleFactoryy.getVehicle("car");
        Vehiclee bike = VehicleFactoryy.getVehicle("bike");
        Vehiclee cycle = VehicleFactoryy.getVehicle("cycle");

        System.out.println(car.getEngineCores());
        System.out.println(bike.getEngineCores());
        System.out.println(cycle.getEngineCores()); // No null check is needed if object exists or not
    }
}
