package ElevatorLLD;

import java.util.List;
import java.util.ArrayList;

// Building will have 2 things :
// 1. Floors
// 2. Elevators

public class Building {
    private List<Floor> floors;
    private List<ElevatorCar> elevators;

    public Building(int numFloors, int numElevators) {
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();

        // Initialize floors
        for (int i = 1; i <= numFloors; i++) {
            floors.add(new Floor());
        }

        // Initialize elevators
        for (int i = 1; i <= numElevators; i++) {
            elevators.add(new ElevatorCar(i));
        }
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void addElevator(ElevatorCar elevator) {
        elevators.add(elevator);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }
}
