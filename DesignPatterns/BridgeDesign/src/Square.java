public class Square extends Shape {

    public Square(Color color){
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Square in " + color.applyColor() + " color");
    }
}