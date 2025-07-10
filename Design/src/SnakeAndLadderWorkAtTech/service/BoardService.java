package service;

import dto.Board;
import dto.Player;
import repository.LadderRepository;
import repository.PlayerRepository;
import repository.SnakeRepository;
import util.DiceUtil;

import java.util.Scanner;

public class BoardService {

    static Board board;

    public static void initializeBoardRepository(Scanner sc) {
        String[] boardInp=sc.nextLine().trim().split(" ");
        board=new Board(Integer.parseInt(boardInp[0]),Integer.parseInt(boardInp[1]),Integer.parseInt(boardInp[2]));
    }

    private int rollDice() {
        return DiceUtil.getDiceValue();
    }

    public void startGame() {
        while(true){
            for(String playerName: PlayerRepository.getPlayerMap().keySet()){

                int newPosition = movePlayer(playerName);
                if(newPosition== board.getEnd()){
                    return;
                }
            }
        }
    }

    private int movePlayer(String playerName) {
        Player player = PlayerRepository.getPlayerMap().get(playerName);
        int currPos = player.getCurrPos();
        if(currPos==board.getEnd()){
            throw  new RuntimeException("Game Over");
        }

        int diceValue = rollDice();
        int newPos = currPos + diceValue;
        if(currPos+diceValue > board.getEnd()){
            newPos = currPos;
        }else if(SnakeRepository.getStartEndMap().containsKey(newPos)){
           newPos = SnakeRepository.getStartEndMap().get(newPos);
        }else if(LadderRepository.getStartEndMap().containsKey(newPos)){
            newPos = LadderRepository.getStartEndMap().get(newPos);
        }

        if(newPos==board.getEnd()){
            System.out.println(playerName + " wins the game");
        }else{
            System.out.println(playerName + " rolled a " +diceValue + " and moved from " + currPos + " to " + newPos);
        }
        player.setCurrPos(newPos);
        return newPos;

    }


}
