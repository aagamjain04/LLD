package ElevatorLLD;

public class Display {
    private int floor;
    private Direction direction;
    private int capacity;

    public void showElevatorDisplay() {
        System.out.println("Floor : "+floor);
        System.out.println("Direction :"+direction);
        System.out.println("Capacity : "+capacity);
    }

    public void showHallDisplay() {
        System.out.println("Current floor : "+floor);
        System.out.println("Direction : "+direction);
    }

    public Display() {
        this.floor = 1;
        this.capacity = 0;
        this.direction = Direction.UP;
    }

    public void updateDisplay(int floor,Direction direction,int capacity){
        this.floor = floor;
        this.direction = direction;
        this.capacity = capacity;
    }

    //getters and setters
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
