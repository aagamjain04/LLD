package repository;

import java.util.HashMap;
import java.util.Map;

public class SnakeRepository {

    static Map<Integer, Integer> startEndMap;

    public SnakeRepository() {
        this.startEndMap = new HashMap<>();
    }

    public static Map<Integer, Integer> getStartEndMap() {
        return startEndMap;
    }
}
