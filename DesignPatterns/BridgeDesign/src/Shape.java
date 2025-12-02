public abstract class Shape {

    protected Color color;

    public Shape(Color color){
        this.color = color;
    }

    abstract public void draw();

    public static class Circle extends Shape {

        public Circle(Color color) {
            super(color);
        }

        @Override
        public void draw() {
            System.out.println("Drawing Shape.Shape.Circle in " + color.applyColor() + " color");

        }
    }
}
