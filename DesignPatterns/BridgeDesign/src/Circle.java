public class Circle extends Shape {
    public Circle(Color color){
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Square in " + color.applyColor() + " color");
    }
}
