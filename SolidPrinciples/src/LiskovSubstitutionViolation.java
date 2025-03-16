interface Bike {
    void turnOnEngine();
    void accelerate();
}

class MotorCycle implements Bike{
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

class Bicycle implements Bike{

    int speed = 0;
    @Override
    public void turnOnEngine() {
        throw new AssertionError("there is no engine");
    }

    @Override
    public void accelerate() {
        speed = speed + 5;
    }
}


public class LiskovSubstitutionViolation {

    public static void main(String[] args) {
        MotorCycle bike = new MotorCycle();
        Bicycle bicycle = new Bicycle();

        bike.turnOnEngine();
        bicycle.turnOnEngine();
    }




}
