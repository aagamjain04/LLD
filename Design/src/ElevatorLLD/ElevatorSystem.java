package ElevatorLLD;

// what will elevator system do?
// 1. first it will get building objects as it has all the elevators
// 2. select the best elevator
// 3. best elevator will be selected on min distance
// 4. request elevator with given floor and direction



public class ElevatorSystem {
    private Building building;

    public ElevatorSystem(Building building){
        this.building = building;
    }

    public void monitoring(){
        System.out.println("Elevator system monitoring active...");
        // Monitor all elevators and their status
        for(ElevatorCar elevator : building.getElevators()){
            System.out.println("Elevator " + elevator.getId() +
                    " - Floor: " + elevator.getCurrentFloor() +
                    " - State: " + elevator.getState() +
                    " - Direction: " + elevator.getCurrentDirection());
        }
    }

    public  void requestElevator(int floor, Direction direction){

        ElevatorCar selectedElevator = selectBestElevator(floor,direction);
        selectedElevator.addRequest(floor,direction);

        System.out.println("\nElevator " + selectedElevator.getId() +
                " assigned to request at floor " + floor +
                " direction " + direction);

        selectedElevator.processRequests();
    }

    public ElevatorCar selectBestElevator(int requestFloor,Direction requestDirection){
        ElevatorCar bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        System.out.println("\nFinding nearest elevator using SCAN algorithm for floor " +
                requestFloor + " direction " + requestDirection);


        for(ElevatorCar elevator : building.getElevators()){
            int distance = calSCANDistance(elevator,requestFloor,requestDirection);
            System.out.println("Elevator " + elevator.getId() +
                    " - Current Floor: " + elevator.getCurrentFloor() +
                    " - Direction: " + elevator.getCurrentDirection() +
                    " - State: " + elevator.getState() +
                    " - SCAN Distance: " + distance);


            if(distance < minDistance){
                bestElevator = elevator;
                minDistance = distance;
            }


        }

        System.out.println("Selected Elevator: " + bestElevator.getId() +
                " with minimum SCAN distance: " + minDistance);

        return bestElevator;

    }

    public int calSCANDistance(ElevatorCar elevatorCar,int requestFloor,Direction requestDirection) {

        int currentFloor = elevatorCar.getCurrentFloor();
        Direction currentDirection = elevatorCar.getCurrentDirection();
        ElevatorState state = elevatorCar.getState();

        // case 1 : when elevator is idle and not moving
        if(state == ElevatorState.IDLE){
            return Math.abs(currentFloor-requestFloor);
        }

        // case 2 : when elevator is moving towards requested floor
        if(currentDirection == requestDirection) {
            if(currentDirection == Direction.UP && requestFloor > currentFloor){
                return  requestFloor - currentFloor;
            }else if(currentDirection == Direction.DOWN && requestFloor < currentFloor) {
                return currentFloor = requestFloor;
            }
        }

        // case 3 : when elevator is moving away from requested floor
        int maxFloor = building.getFloors().size();
        int minFloor = 1;

        if(currentDirection == Direction.UP){
            return (maxFloor-currentFloor) + (maxFloor-requestFloor);
        }else{
            return (currentFloor-minFloor) + (requestFloor - minFloor);
        }


    }


}
