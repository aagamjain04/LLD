### Class Diagram

![img.png](img.png)


## **Core OOP Principles Used:**

### 1. **Encapsulation**

- **Private fields** with **public getter/setter methods**
- Example in `ElevatorCar`:

java

```java
private int id;
private Door door;
private ElevatorState state;

public int getId() { return id; }
public Door getDoor() { return door; }
```

### 2. **Abstraction**

- **Enums** abstract complex states: `ElevatorState`, `Direction`, `DoorState`
- **Method abstraction** hides complex logic:

java

```java
public void processSCANRequests() // Hides SCAN algorithm complexity
public ElevatorCar selectBestElevatorCar() // Hides selection logic
```

### 3. **Composition**

- **"Has-a" relationships** throughout the system:

java

```java
// ElevatorCar HAS-A Door, Display, List of Buttons
private Door door;
private Display display;
private List<ElevatorButton> panel;

// Building HAS-A list of Floors and Elevators
private List<Floor> floors;
private List<ElevatorCar> elevators;
```



## **Design Patterns Used:**

### 1. **Strategy Pattern** (Implicit)

- The **SCAN algorithm** is implemented as a strategy for elevator selection
- Could be extended to support different algorithms (FCFS, SSTF, etc.)

java

```java
private int calculateSCANDistance() // Strategy for distance calculation
public ElevatorCar selectBestElevatorCar() // Strategy for elevator selection
```

### 2. **State Pattern**

- **ElevatorState enum** manages elevator states: `IDLE`, `UP`, `DOWN`
- **DoorState enum** manages door states: `OPEN`, `CLOSE`
- State transitions are handled in methods like `move()`, `stop()`

### 3. **Composite Pattern**

- **Building** contains **Floors** and **ElevatorCars**
- **Floor** contains **Displays** and **HallButtons**
- **ElevatorCar** contains **Door**, **Display**, and **ElevatorButtons**

### 4. **Observer Pattern** (Framework for)

- **Display** class can show elevator status
- **Monitoring** method observes all elevator states
- Could be extended for real-time updates

### 5. **Command Pattern** (Implicit)

- **Requests** are encapsulated as objects with floor and direction
- **addRequest()** method stores commands for later execution

java

```java
public void addRequest(int floor, Direction direction)
```

### 6. **Singleton Pattern** (Potential)

- **ElevatorSystem** could be implemented as singleton for building-wide control
- **Building** represents a single building instance

## **Additional OOP Concepts:**

### 7. **Data Encapsulation with Collections**

java

```java
private Set<Integer> upRequests;
private Set<Integer> downRequests;
private List<ElevatorCar> elevators;
```

### 8. **Method Responsibility**

- Each class has **single responsibility**:
    - `Door`: Handle door operations
    - `Display`: Handle information display
    - `ElevatorCar`: Handle elevator movement and logic
    - `ElevatorSystem`: Handle system-wide coordination

