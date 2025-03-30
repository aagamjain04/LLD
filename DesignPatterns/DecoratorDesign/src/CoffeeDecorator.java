// Base Decorator
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription(){
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost(){
        return decoratedCoffee.getCost();
    }



}
