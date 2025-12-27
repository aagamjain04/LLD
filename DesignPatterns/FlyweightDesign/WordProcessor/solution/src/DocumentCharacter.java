
// Concrete Flyweight (Class) - implements the Flyweight interface and stores intrinsic state
public class DocumentCharacter implements ILetter{

    // intrinsic data - shared data - common to all objects
    private final char character;
    private final String fontType;
    private final int size;

    DocumentCharacter(char character, String fontType, int size) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
    }


    @Override
    public void display(int row, int column) {
        //display the character of particular font and size at given location
        System.out.println("Displaying " + character + " at row " + row + " and column " + column);

    }
}
