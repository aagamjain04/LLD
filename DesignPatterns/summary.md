# Low-Level Design in a Hurry

## Design Patterns

A list of design patterns for low-level design interviews.

Design patterns are reusable building blocks for solving common design problems. They're names for structures you naturally create when you follow solid design principles.

The Gang of Four catalog, written in 1994 for C++ and Smalltalk, defined 23 patterns that became required reading for software engineers. The book earned legendary status, but the reality is most of those patterns don't matter anymore. Modern languages have built-in features that replaced half of them ie. iterators are primitives now, not patterns. The shift from inheritance-heavy OOP to composition and functional programming made others obsolete. And in interviews, you'll get asked about maybe five patterns total, not twenty-three.

Most online resources still dutifully list all 23 GoF patterns like they're equally important. They're not. We're cutting the historical baggage and focusing on the patterns that actually show up in modern LLD interviews. The ones below are what interviewers ask about and what you'll use in real designs.

As you learn these patterns, keep in mind that the goal isn't to force them into every solution. You should only use a pattern when the problem naturally calls for it. Forcing one is a common mistake that signals over-engineering. Patterns arise from good design decisions rather than driving them, and the most frequent error is trying to fit a pattern where it doesn't belong.

Design patterns are handled differently depending on where you interview. In the US, most LLD interviews don't explicitly test whether you can name patterns---you're evaluated on the quality of your design. In other parts of the world, particularly India, interviewers are more likely to ask about patterns directly. While we align with the former, both approaches have merit, and we want you prepared for either reality you may encounter.

Patterns fit cleanly into three categories: creational, structural, and behavioral. Let's walk through each category together.

## Creational Patterns

Creational patterns control how objects get created. They hide construction details, let you swap implementations, and keep your code from being tightly coupled to specific classes.

### Factory Method

A factory is a helper that makes the right kind of object for you so you don't have to decide which one to create. They're used to hide creation logic and keep your code flexible when the exact type you need can change.

Factory shows up regularly in interviews, usually when requirements say "support different notification types" or "handle multiple payment methods." Instead of writing `new EmailNotification()` throughout your code, you call `notificationFactory.create(type)`. Now when you add SMS notifications, you update the factory. The rest of your code never changes.

**FactoryMethod.java**

```java
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        // Email sending logic
    }
}

class SMSNotification implements Notification {
    public void send(String message) {
        // SMS sending logic
    }
}

class NotificationFactory {
    public static Notification create(String type) {
        if (type.equals("email")) {
            return new EmailNotification();
        } else if (type.equals("sms")) {
            return new SMSNotification();
        }
        throw new IllegalArgumentException("Unknown type");
    }
}

// Usage
Notification notif = NotificationFactory.create("email");
notif.send("Hello");
```

The factory centralizes creation logic. When you add push notifications, you modify one place. Factory controls which object gets instantiated. It makes the decision once and returns the right type.

Factories are polarizing. While very popular and idiomatic in languages like Java, some engineers see them as examples of overengineering. If you choose to implement one, take a look at your interviewer and check them for a grimace.

### Builder

A builder is a helper that lets you create a complex object step by step without worrying about the order or messy construction details. It's used when an object has many optional parts or configuration choices.

This shows up when designing things like HTTP requests, database queries, or configuration objects. Instead of a constructor with ten parameters where half are null, you build the object incrementally.

**Builder.java**

```java
class HttpRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;

    private HttpRequest() {}

    public static class Builder {
        private HttpRequest request = new HttpRequest();

        public Builder url(String url) {
            request.url = url;
            return this;
        }

        public Builder method(String method) {
            request.method = method;
            return this;
        }

        public Builder header(String key, String value) {
            if (request.headers == null) {
                request.headers = new HashMap<>();
            }
            request.headers.put(key, value);
            return this;
        }

        public Builder body(String body) {
            request.body = body;
            return this;
        }

        public HttpRequest build() {
            // Validate required fields
            if (request.url == null) {
                throw new IllegalStateException("URL is required");
            }
            return request;
        }
    }
}

// Usage
HttpRequest request = new HttpRequest.Builder()
    .url("https://api.example.com")
    .method("POST")
    .header("Content-Type", "application/json")
    .body("{\"key\": \"value\"}")
    .build();
```

Builder makes construction readable and handles optional fields cleanly. It most commonly shows up in LLD interviews when you're designing API clients or complex configurations, but is very rarely used in other contexts.

If the interviewer didn't describe a complex object with lots of optional details, Builder probably isn't needed. Most interview problems involve simple domain objects with 2-4 required fields where a normal constructor works fine.

### Singleton

Singleton ensures only one instance of a class exists. Use it when you need exactly one shared resource like a configuration manager, connection pool, or logger.

Most of the time you don't actually need a Singleton. You can just pass shared objects through constructors instead --- it's clearer and easier to test. Singletons hide dependencies and make testing harder.

**Singleton.java**

```java
class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        // Private constructor prevents external instantiation
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql) {
        // Database operations
    }
}

// Usage
DatabaseConnection db = DatabaseConnection.getInstance();
db.query("SELECT * FROM users");
```

It's the `getInstance` method that makes this a Singleton. It ensures that only one instance of the class is created and returned, if called multiple times, it will return the same instance.

In interviews, know what Singleton is and when not to use it. If an interviewer asks "should this be a Singleton?", the answer is usually no unless they explicitly want a single shared instance across the entire system. There are thread-safe versions of Singleton, but interviewers don't expect you to implement them in LLD interviews.

## Structural Patterns

Structural patterns deal with how objects connect to each other. They help you build flexible relationships between classes without creating tight coupling or messy dependencies.

### Decorator

A decorator adds behavior to an object without changing its class. Use it when you need to layer on extra functionality at runtime.

Decorator is powerful but comes up less often than Strategy or Observer. You might need this when the requirements say things like "add logging to specific operations" or "encrypt certain messages." Instead of creating subclasses for every combination (`LoggedEmailNotification`, `EncryptedEmailNotification`, `LoggedEncryptedEmailNotification`), you wrap the base object with decorators. If you see words like "optional features," "stack behaviors," or "combine multiple enhancements," think Decorator.

**Decorator.java**

```java
interface DataSource {
    void writeData(String data);
    String readData();
}

class FileDataSource implements DataSource {
    private String filename;

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    public void writeData(String data) {
        // Write to file
    }

    public String readData() {
        // Read from file
        return "data from file";
    }
}

class EncryptionDecorator implements DataSource {
    private DataSource wrapped;

    public EncryptionDecorator(DataSource source) {
        this.wrapped = source;
    }

    public void writeData(String data) {
        String encrypted = encrypt(data);
        wrapped.writeData(encrypted); // Delegate to wrapped object
    }

    public String readData() {
        String data = wrapped.readData();
        return decrypt(data);
    }

    private String encrypt(String data) {
        return "encrypted:" + data;
    }

    private String decrypt(String data) {
        return data.replace("encrypted:", "");
    }
}

class CompressionDecorator implements DataSource {
    private DataSource wrapped;

    public CompressionDecorator(DataSource source) {
        this.wrapped = source;
    }

    public void writeData(String data) {
        String compressed = compress(data);
        wrapped.writeData(compressed); // Delegate to wrapped object
    }

    public String readData() {
        String data = wrapped.readData();
        return decompress(data);
    }

    private String compress(String data) {
        return "compressed:" + data;
    }

    private String decompress(String data) {
        return data.replace("compressed:", "");
    }
}

// Usage
DataSource source = new FileDataSource("data.txt");
source = new EncryptionDecorator(source);
source = new CompressionDecorator(source);
source.writeData("sensitive info");
// Data gets compressed, then encrypted, then written to file
```

Use a Decorator when you need to add behavior at runtime based on conditions, like wrapping a service with logging only in debug mode or adding caching only for certain requests. It lets you layer optional, combinable features without modifying the underlying class. In most other cases, use normal subclasses, where the new behavior is fixed at design time and represents a stable variation of the original type. If the behavior depends on runtime conditions, choose Decorator; if it's a predefined type difference, choose Subclass.

Each decorator adds one piece of functionality. You can stack them in any order and add or remove them without touching the base class or other decorators, though in real systems order often affects behavior.

### Facade

A facade is just a coordinator class that hides complexity. You're probably already building facades in every LLD interview without calling them that. Your `Game` class in Tic Tac Toe? That's a facade. Any orchestrator that coordinates multiple components behind a clean interface? Also a facade.

Almost nobody names this pattern when they're using it. The pattern name is more useful when you're wrapping existing messy code. Like, if you inherit a complex subsystem with awkward APIs, you write a facade to make it easier to use. But in LLD interviews, you're designing clean orchestrators from scratch, which happens to be the same structure. You're likely already doing the right thing instinctively, you just don't need to announce it.

**Facade.java**

```java
enum GameState {
    IN_PROGRESS,
    WON,
    DRAW
}

class Board {
    public boolean placeMark(int row, int col, String mark) {
        // Place mark logic
        return true;
    }

    public boolean checkWin(int row, int col) {
        // Check win logic
        return false;
    }

    public boolean isFull() {
        // Check if board is full
        return false;
    }
}

class Player {
    private String mark;

    public Player(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}

class Game {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameState state;

    public Game() {
        this.board = new Board();
        this.playerX = new Player("X");
        this.playerO = new Player("O");
        this.currentPlayer = playerX;
        this.state = GameState.IN_PROGRESS;
    }

    public boolean makeMove(int row, int col) {
        // Coordinates board, player, and state logic
        // Caller doesn't need to understand internal details
        if (state != GameState.IN_PROGRESS) return false;
        if (!board.placeMark(row, col, currentPlayer.getMark())) return false;

        if (board.checkWin(row, col)) {
            state = GameState.WON;
        } else if (board.isFull()) {
            state = GameState.DRAW;
        } else {
            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }
        return true;
    }
}

// Usage - simple interface hides all the coordination
Game game = new Game();
game.makeMove(0, 0);
game.makeMove(1, 1);
```

The pattern name just describes what good orchestrator design looks like. Build it naturally, name it if it helps communicate, but don't worry if you never mention Facade by name in an interview.

## Behavioral Patterns

Behavioral patterns control how objects interact and distribute responsibilities. They're about the flow of control and communication between objects.

### Strategy

Strategy replaces conditional logic with polymorphism. Use it when you have different ways of doing the same thing and you want to swap them at runtime.

When we say "runtime," we mean the moment the program is actually running. You can choose behaviors based on conditions, inputs, or configuration as the code executes. When we say "compile time," we mean decisions baked into the code itself. The behavior is fixed in the class definition and doesn't change while the program runs.

Interviewers love Strategy. It's the single most common pattern in LLD interviews because it directly tests whether you understand polymorphism and composition over inheritance. When you see a pile of `if/else` or `switch` statements based on type, that's a strategy pattern waiting to happen. If you learn one pattern from this page, make it this one. You've already seen this in the OOP concepts page with the parking lot vehicle example.

**Strategy.java**

```java
interface PaymentStrategy {
    boolean pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean pay(double amount) {
        // Credit card processing logic
        System.out.println("Paid " + amount + " with credit card");
        return true;
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public boolean pay(double amount) {
        // PayPal processing logic
        System.out.println("Paid " + amount + " with PayPal");
        return true;
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}

// Usage
ShoppingCart cart = new ShoppingCart();

cart.setPaymentStrategy(new CreditCardPayment("1234-5678"));
cart.checkout(100.00);

cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
cart.checkout(50.00);
```

Instead of having checkout logic full of `if (paymentType == "credit")` statements, each payment method handles itself. This is just polymorphism with a pattern name. Strategy swaps behavior at runtime through composition. The cart holds a reference to a strategy and delegates to it. Factory decides which type to instantiate. Strategy decides which behavior to use after the object already exists.

### Observer

Observer lets objects subscribe to events and get notified when something happens. Use it when changes in one object need to trigger updates in other objects.

Observer is a top-tier interview pattern. It shows up when you're designing systems where multiple components care about state changes---a stock price changes and multiple displays need to update, or a user places an order and inventory, notifications, and analytics all need to know. If the problem involves the words "notify" or "update multiple components," you're probably looking at Observer.

**Observer.java**

```java
interface Observer {
    void update(String symbol, double price);
}

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

class Stock implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String symbol;
    private double price;

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers(); // Price changed, tell everyone
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }
}

class PriceDisplay implements Observer {
    public void update(String symbol, double price) {
        System.out.println("Display updated: " + symbol + " = $" + price);
    }
}

class PriceAlert implements Observer {
    private double threshold;

    public PriceAlert(double threshold) {
        this.threshold = threshold;
    }

    public void update(String symbol, double price) {
        if (price > threshold) {
            System.out.println("Alert! " + symbol + " exceeded $" + threshold);
        }
    }
}

// Usage
Stock stock = new Stock("AAPL");

PriceDisplay display = new PriceDisplay();
PriceAlert alert = new PriceAlert(150.00);

stock.attach(display);
stock.attach(alert);

stock.setPrice(145.00); // Both observers get notified
stock.setPrice(155.00); // Both observers get notified
```

When the stock price changes, every attached observer gets updated automatically. No need for the stock to know what the observers do with the information.

### State Machine

A state machine handles state transitions cleanly. Use it when an object's behavior changes based on its internal state and you have complex state transition rules. You'll also see this called the "State pattern" in some references, but state machine is the more common term.

State machines are less common than Strategy or Observer, but when you need one, it's usually the centerpiece of your entire design. If there's a state machine in your solution, the interview is probably organized around it---it's the most important thing to talk through. This shows up in LLD interviews when you're designing things like vending machines, document workflows, or game states. If the word "state" appears multiple times in the requirements, you're probably looking at a state machine. Instead of scattered conditionals checking current state everywhere, you encapsulate each state's behavior in its own class.

Drawing a state diagram is one of the best ways to communicate a state machine design in an interview. Show the states as circles, transitions as arrows labeled with actions, and it becomes immediately clear how the system works. Interviewers appreciate the visual---it shows you're thinking clearly about the problem.

**StateMachine.java**

```java
interface VendingMachineState {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispense(VendingMachine machine);
}

class NoCoinState implements VendingMachineState {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted");
        machine.setState(new HasCoinState());
    }

    public void selectProduct(VendingMachine machine) {
        System.out.println("Insert coin first");
    }

    public void dispense(VendingMachine machine) {
        System.out.println("Insert coin first");
    }
}

class HasCoinState implements VendingMachineState {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin already inserted");
    }

    public void selectProduct(VendingMachine machine) {
        System.out.println("Product selected");
        machine.setState(new DispenseState());
    }

    public void dispense(VendingMachine machine) {
        System.out.println("Select product first");
    }
}

class DispenseState implements VendingMachineState {
    public void insertCoin(VendingMachine machine) {
        System.out.println("Please wait, dispensing");
    }

    public void selectProduct(VendingMachine machine) {
        System.out.println("Please wait, dispensing");
    }

    public void dispense(VendingMachine machine) {
        System.out.println("Dispensing product");
        machine.setState(new NoCoinState());
    }
}

class VendingMachine {
    private VendingMachineState currentState;

    public VendingMachine() {
        currentState = new NoCoinState();
    }

    public void insertCoin() {
        currentState.insertCoin(this);
    }

    public void selectProduct() {
        currentState.selectProduct(this);
    }

    public void dispense() {
        currentState.dispense(this);
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
}

// Usage
VendingMachine machine = new VendingMachine();

machine.selectProduct(); // "Insert coin first"
machine.insertCoin(); // "Coin inserted"
machine.selectProduct(); // "Product selected"
machine.dispense(); // "Dispensing product"
```

Each state knows which state comes next and what actions are valid. No giant switch statements checking current state in every method.

## Wrapping Up

Patterns only help when they match the problem you're solving. Most interview-ready designs use no patterns, or at most one or two. If you're reaching for three or more, you're probably forcing it and over-engineering.

Here's the quick pattern cheat sheet, grouped by category:

**Creational Patterns**

- **Factory** → Use when callers shouldn't care which concrete class gets created.
- **Builder** → Use when an object has lots of optional fields or messy construction details.
- **Singleton** → Use when you truly need one global instance (rare in interviews).

**Structural Patterns**

- **Decorator** → Use when you need to layer optional behaviors at runtime without subclass explosion.
- **Facade** → Use when you want to hide internal complexity behind a simple entry point.

**Behavioral Patterns**

- **Strategy** → Use when you're replacing if/else logic with interchangeable behaviors.
- **Observer** → Use when multiple components need to react to a single event.
- **State Machine** → Use when an object's behavior depends on its current state and transitions get messy.

Factory, Strategy, and Observer are the patterns you'll reach for most often. State machines, Decorator, and Adapter show up when the requirements call for them. Builder, Singleton, Facade, and Composite are worth recognizing but rarely central to an interview solution.

Focus on solving the problem cleanly and name the pattern afterward if it fits.