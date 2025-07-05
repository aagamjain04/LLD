package CarRentalSystemLLD;

import CarRentalSystemLLD.Product.Vehicle;
import CarRentalSystemLLD.Product.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleInventoryManagement {

    Map<VehicleType, List<Vehicle>> vehicleTypeListMap;

    public VehicleInventoryManagement() {
        vehicleTypeListMap = new HashMap<>();
        vehicleTypeListMap.put(VehicleType.CAR,new ArrayList<>());
        vehicleTypeListMap.put(VehicleType.MOTORCYCLE,new ArrayList<>());
    }

    public void addVehicle(Vehicle vehicle) {
        List<Vehicle> vehicleList = vehicleTypeListMap.get(vehicle.getType());
        vehicleList.add(vehicle);
        vehicleTypeListMap.put(vehicle.getType(),vehicleList);
    }

    public void removeVehicle(Vehicle vehicle) {
        List<Vehicle> vehicleList = vehicleTypeListMap.get(vehicle.getType());
        vehicleList.remove(vehicle);
        vehicleTypeListMap.put(vehicle.getType(),vehicleList);
    }

    // CRUD Operation

    public List<Vehicle> getVehicles(VehicleType vehicleType){
        return vehicleTypeListMap.get(vehicleType);
    }

}
