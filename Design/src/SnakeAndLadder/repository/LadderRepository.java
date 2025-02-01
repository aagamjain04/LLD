package repository;

import java.util.HashMap;
import java.util.Map;

public class LadderRepository {
    static Map<Integer, Integer> startEndMap;

    public LadderRepository() {
        this.startEndMap = new HashMap<>();
    }

    public static Map<Integer, Integer> getStartEndMap() {
        return startEndMap;
    }
}
