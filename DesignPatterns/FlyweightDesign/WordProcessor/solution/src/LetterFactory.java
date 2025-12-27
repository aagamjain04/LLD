import java.util.HashMap;
import java.util.Map;

public class LetterFactory {

    private static final Map<java.lang.Character, ILetter> characterCache = new HashMap<>();

    public static ILetter createLetter(char charValue){
        // Check if letter already exists in cache
        if(characterCache.containsKey(charValue)) {
            System.out.println("Returning cached letter: " + charValue);
            return characterCache.get(charValue);
        }

        // Create new DocumentCharacter (intrinsic state: character, font, size)
        System.out.println("Creating new letter: " + charValue);
        ILetter newLetter = new DocumentCharacter(charValue, "Arial", 12);
        characterCache.put(charValue, newLetter);

        return newLetter;
    }
}
