package ElevatorLLD;

public class Door {
    private DoorState doorState;

    public Door() {
        this.doorState = DoorState.CLOSE;
    }

    public Boolean isOpen() {
        return doorState == DoorState.OPEN;
    }
    public void openDoor() {
        this.doorState = DoorState.OPEN;
        System.out.println("Door Opened");
    }

    public void closeDoor() {
        this.doorState = DoorState.CLOSE;
        System.out.println("Door Closed");
    }

    public DoorState getState() {
        return doorState;
    }
}
