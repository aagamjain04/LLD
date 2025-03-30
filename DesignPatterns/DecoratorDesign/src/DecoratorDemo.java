public class DecoratorDemo {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Cost: Rs" + simpleCoffee.getCost() + ", Description: " + simpleCoffee.getDescription());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Cost: Rs" + milkCoffee.getCost() + ", Description: " + milkCoffee.getDescription());

        // Coffee with milk and sugar
        Coffee milkSugarCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Cost: Rs" + milkSugarCoffee.getCost() + ", Description: " + milkSugarCoffee.getDescription());

        // Coffee with milk, sugar, and whipped cream
        Coffee fancyCoffee = new WhippedCreamDecorator(milkSugarCoffee);
        System.out.println("Cost: Rs" + fancyCoffee.getCost() + ", Description: " + fancyCoffee.getDescription());

        // Creating fancy coffee in one line
        Coffee superFancy = new WhippedCreamDecorator(new SugarDecorator(new MilkDecorator(new SimpleCoffee())));
        System.out.println("Cost: Rs" + superFancy.getCost() + ", Description: " + superFancy.getDescription());

    }
}
