import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

// JAVA Collections usage example
public class LinkedHashSetExample {

    public static void main(String[] args) {
        Set<Integer> integerSet = new LinkedHashSet<>();
        integerSet.add(2);
        integerSet.add(77);
        integerSet.add(82);
        integerSet.add(63);
        integerSet.add(5);
        integerSet.add(77);

        Iterator<Integer> it = integerSet.iterator();

        while(it.hasNext()){
            int value = it.next();
            System.out.println(value);
        }

    }

}
