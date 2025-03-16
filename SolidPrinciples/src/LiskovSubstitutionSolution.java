interface Bike1 {
    void accelerate();
}

interface MotorBike extends  Bike1{
    void turnOnEngine();
}

class MotorCycle1 implements MotorBike{
    boolean isEngineOn;
    int speed = 0;
    @Override
    public void turnOnEngine() {
        isEngineOn = true;
    }

    @Override
    public void accelerate() {
        speed = speed + 10;
    }
}

class Bicycle1 implements Bike1{

    int speed = 0;


    @Override
    public void accelerate() {
        speed = speed + 5;
    }
}


public class LiskovSubstitutionSolution {

    public static void main(String[] args) {
        MotorCycle1 bike = new MotorCycle1();
        Bicycle1 bicycle = new Bicycle1();

        bike.turnOnEngine();
        bicycle.accelerate();
    }




}
