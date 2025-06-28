package ElevatorLLD;

public class ElevatorSystemDemo {
    public static void main(String[] args) {


        // Create a building with 10 floors and 3 elevators
        Building building = new Building(10,3);
        ElevatorSystem elevatorSystem = new ElevatorSystem(building);

        // Set initial position of elevators
        building.getElevators().get(0).setCurrentFloor(1);
        building.getElevators().get(0).setCurrentDirection(Direction.UP);

        building.getElevators().get(1).setCurrentFloor(5);
        building.getElevators().get(1).setCurrentDirection(Direction.DOWN);

        building.getElevators().get(2).setCurrentFloor(8);
        building.getElevators().get(2).setCurrentDirection(Direction.UP);


        System.out.println("=== ELEVATOR SYSTEM DEMO ===");
        System.out.println("Building: 10 floors, 3 elevators");
        System.out.println("\nInitial Status:");
        elevatorSystem.monitoring();

        System.out.println("\n" + "=".repeat(50));

        System.out.println("SCENARIO 1: Request from floor 3, going UP");
        elevatorSystem.requestElevator(3, Direction.UP);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 2: Request from floor 7, going DOWN");
        elevatorSystem.requestElevator(7, Direction.DOWN);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 3: Request from floor 9, going UP");
        elevatorSystem.requestElevator(9, Direction.UP);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("Final Status:");
        elevatorSystem.monitoring();

        // Demonstrate multiple requests to same elevator
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 4: Multiple requests to demonstrate SCAN");

        ElevatorCar elevator = building.getElevators().get(0);
        elevator.setCurrentFloor(1);
        elevator.setCurrentDirection(Direction.UP);
        elevator.setState(ElevatorState.IDLE);

        // Add multiple requests
        elevator.addRequest(3, Direction.UP);
        elevator.addRequest(7, Direction.UP);
        elevator.addRequest(5, Direction.DOWN);
        elevator.addRequest(2, Direction.DOWN);

        System.out.println("Added requests: Floor 3(UP), Floor 7(UP), Floor 5(DOWN), Floor 2(DOWN)");
        System.out.println("Starting from floor 1, direction UP");
        System.out.println("SCAN will process: UP requests first (3, 7), then DOWN requests (5, 2)");

        elevator.processRequests();

    }
}
