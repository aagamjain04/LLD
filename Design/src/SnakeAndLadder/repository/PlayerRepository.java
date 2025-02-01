package repository;

import dto.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepository {


    static Map<String,Player> playerMap;

    public PlayerRepository() {
        playerMap = new HashMap<>();
    }

    public static Map<String, Player> getPlayerMap() {
        return playerMap;
    }
}
