public class VehicleFactoryy {

    public static Vehiclee getVehicle(String type){
        if("car".equals(type)){
            return new Carr();
        }else if("bike".equals(type)){
            return new Bikee();
        }else{
            return new NullVehicle();
        }
    }

}
