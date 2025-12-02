public class BridgeDesignDemo {


    public static void main(String[] args) {
        Color red = new Red();
        Color blue = new Blue();

        Circle redCircle = new Circle(red);
        Circle blueCircle = new Circle(blue);
        redCircle.draw();
        blueCircle.draw();

        Square redSquare = new Square(red);
        Square blueSquare = new Square(blue);
        redSquare.draw();
        blueSquare.draw();

    }



}
