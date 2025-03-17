// Separate interfaces for different responsibilities
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

// Human worker implements all interfaces
class HumanWorker1 implements Workable, Eatable, Sleepable {
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

// Robot worker only implements the relevant interface
class RobotWorker1 implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working");
    }
}
public class InterfaceSegregationSolution {
}
