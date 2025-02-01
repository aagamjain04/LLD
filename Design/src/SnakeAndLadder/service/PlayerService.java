package service;

import dto.Player;
import repository.PlayerRepository;

import java.util.Map;
import java.util.Scanner;

public class PlayerService {
    static void initializePlayerRepository(Scanner sc){
        PlayerRepository playerRepository = new PlayerRepository();
        int playerNum = sc.nextInt();
        sc.nextLine();
        while ((playerNum--)>0){
            String playerName = sc.nextLine();
            Player player = new Player(playerName,0);
            PlayerRepository.getPlayerMap().put(playerName,player);
        }

    }
}
