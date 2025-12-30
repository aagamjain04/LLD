// Base Decorator
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.coffee = decoratedCoffee;
    }




}
