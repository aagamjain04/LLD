package SnakeAndLadderLLD;

import java.util.*;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private Map<String,Integer> playerPositions;

    public Game(Board board, Dice dice, List<Player> playerList){
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(playerList);
        this.playerPositions = new HashMap<>();

        for(Player player : playerList){
            playerPositions.put(player.getName(),0);
        }
    }

    public void startGame(){
        while(true){
            Player currentPlayer = players.poll();
            int currentPos = playerPositions.get(currentPlayer.getName());

            int diceValue = dice.rollDice();
            int nextPos = currentPos+diceValue;

            if(nextPos > board.getSize()){
                players.offer(currentPlayer);
                continue;
            }

            int finalPos = board.getNextPosition(nextPos);

            playerPositions.put(currentPlayer.getName(), finalPos);

            System.out.println(currentPlayer.getName() + " rolled a " + diceValue +
                    " and moved from " + currentPos + " to " + finalPos);

            if (finalPos == board.getSize()) {
                System.out.println(currentPlayer.getName() + " wins the game!");
                break;
            }

            players.offer(currentPlayer);

        }
    }

}
