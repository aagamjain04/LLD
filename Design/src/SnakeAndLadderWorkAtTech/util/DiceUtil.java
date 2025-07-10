package util;
import java.util.Random;
public class DiceUtil {

    static public int getDiceValue(){
        Random random = new Random();
        int randInt = random.nextInt(5);
        return randInt+1;
    }
}
