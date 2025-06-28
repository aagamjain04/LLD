package ElevatorLLD;

// what will elevator car have?
// 1. an elevator id
// 2. Door
// 3. ElevatorState
// 4. ElevatorButton
// 5. Display
// 6. Current floor of elevator
// 7. Up requests
// 8. Down Requests
// 9. Current direction of elevator

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ElevatorCar {

    private int id;
    private Door door;
    private ElevatorState state;
    private Display display;
    private List<ElevatorButton> panel;
    private int currentFloor;
    private TreeSet<Integer> upRequests;
    private TreeSet<Integer> downRequests;
    private Direction currentDirection;

    //constructor
    public ElevatorCar(int id){
        this.id = id;
        this.door = new Door();
        this.state = ElevatorState.IDLE;
        this.display = new Display();
        this.panel = new ArrayList<>();
        this.currentFloor = 1;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>();
        this.currentDirection = Direction.UP;
    }

    // elevator is a dumb object, it will just receive request to go up or down to a particular floor

    public void addRequest(int floor,Direction direction){
        if(direction == Direction.UP){
            upRequests.add(floor);
        }else {
            downRequests.add(floor);
        }
    }


    // SCAN Algorithm Implementation to process requests

    public void processRequests() {
        while(hasRequests()){
            if(currentDirection == Direction.UP){
                processUPRequests();
                if(upRequests.isEmpty() && !downRequests.isEmpty()){
                    currentDirection = Direction.DOWN;
                    state = ElevatorState.DOWN;
                }
            } else {
                processDOWNRequests();
                if(downRequests.isEmpty() && !upRequests.isEmpty()){
                    currentDirection = Direction.UP;
                    state = ElevatorState.UP;
                }
            }

            if(!hasRequests()){
                state = ElevatorState.IDLE;
                break;
            }
        }
    }


    boolean hasRequests(){
        return !upRequests.isEmpty() || !downRequests.isEmpty();
    }

    public void processUPRequests(){
        currentDirection = Direction.UP;

        while(!upRequests.isEmpty()){
            Integer nextFloor = upRequests.ceiling(currentFloor);
            if(nextFloor == null){
                break; // no more floors in up direction
            }

            // Move to nextFloor

            while(currentFloor < nextFloor){
                move();
            }

            stop();
            upRequests.remove(nextFloor);
        }

    }

    public void processDOWNRequests(){
        currentDirection = Direction.DOWN;

        while(!downRequests.isEmpty()){
            Integer nextFloor = downRequests.floor(currentFloor);
            if(nextFloor == null){
                break; // no more floors in down direction
            }

            // Move to nextFloor

            while(currentFloor > nextFloor){
                move();
            }

            stop();
            downRequests.remove(nextFloor);
        }

    }

    public void move(){
        if(currentDirection == Direction.UP){
            currentFloor++;
            System.out.println("Elevator " + id + " moving up to floor " + currentFloor);
        }else {
            currentFloor--;
            System.out.println("Elevator " + id + " moving down to floor " + currentFloor);
        }

        display.updateDisplay(currentFloor,currentDirection,0);
    }

    public void stop() {
        state = ElevatorState.IDLE;
        System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
        door.openDoor();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        door.closeDoor();
    }


    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public List<ElevatorButton> getPanel() {
        return panel;
    }

    public void setPanel(List<ElevatorButton> panel) {
        this.panel = panel;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public TreeSet<Integer> getUpRequests() {
        return upRequests;
    }

    public void setUpRequests(TreeSet<Integer> upRequests) {
        this.upRequests = upRequests;
    }

    public TreeSet<Integer> getDownRequests() {
        return downRequests;
    }

    public void setDownRequests(TreeSet<Integer> downRequests) {
        this.downRequests = downRequests;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
