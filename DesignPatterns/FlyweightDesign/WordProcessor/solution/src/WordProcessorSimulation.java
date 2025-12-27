import java.lang.Character;

public class WordProcessorSimulation {
    public static void main(String[] args) {
        System.out.println("==== Flyweight Design Pattern ====");

        // Data: "Hello World"
        // Total 11 characters (including space)
        // h = 1 time
        // e = 1 time
        // l = 3 times (reused)
        // o = 2 times (reused)
        // w = 1 time
        // r = 1 time
        // d = 1 time
        // ' ' = 1 time

        // Create 11 character objects and provide display position(extrinsic state) at runtime

        ILetter object1 = LetterFactory.createLetter('H');
        object1.display(0, 0);

        ILetter object2 = LetterFactory.createLetter('e');
        object2.display(0, 1);

        ILetter object3 = LetterFactory.createLetter('l');
        object3.display(0, 2);

        ILetter object4 = LetterFactory.createLetter('l');
        object4.display(0, 3);

        ILetter object5 = LetterFactory.createLetter('o');
        object5.display(0, 4);

        ILetter object6 = LetterFactory.createLetter(' ');
        object6.display(0, 5);

        ILetter object7 = LetterFactory.createLetter('W');
        object7.display(0, 6);

        ILetter object8 = LetterFactory.createLetter('o');
        object8.display(0, 7);

        ILetter object9 = LetterFactory.createLetter('r');
        object9.display(0, 8);

        ILetter object10 = LetterFactory.createLetter('l');
        object10.display(0, 9);

        ILetter object11 = LetterFactory.createLetter('d');
        object11.display(0, 10);

        System.out.println("\nFlyweight objects created. Notice how 'l' and 'o' are reused from the factory cache!");
    }

}
