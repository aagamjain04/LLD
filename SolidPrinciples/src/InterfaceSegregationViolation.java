interface Worker {
    void work();
    void eat();
    void sleep();
}

// Human worker can do all these things
class HumanWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Human working");
    }

    @Override
    public void eat() {
        System.out.println("Human eating");
    }

    @Override
    public void sleep() {
        System.out.println("Human sleeping");
    }
}

// Robot worker is forced to implement methods it doesn't need
class RobotWorker implements Worker {
    @Override
    public void work() {
        System.out.println("Robot working");
    }

    @Override
    public void eat() {
        // Robots don't eat, but forced to implement this
        throw new UnsupportedOperationException("Robots don't eat");
    }

    @Override
    public void sleep() {
        // Robots don't sleep, but forced to implement this
        throw new UnsupportedOperationException("Robots don't sleep");
    }
}
public class InterfaceSegregationViolation {

}
