public class Main {
    public static void main(String[] args) {

        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("Images created (not loaded yet)");

        // Image is loaded only when display() is called
        image1.display();
        image1.display(); // Second call - no loading

        image2.display();
    }
}
