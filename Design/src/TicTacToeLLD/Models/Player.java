package TicTacToeLLD.Models;

public class Player {

    String playerName;
    PlayerMove playerMove;

    public Player(String playerName, PlayerMove playerMove) {
        this.playerName = playerName;
        this.playerMove = playerMove;
    }

    public String getPlayerName(){
        return playerName;
    }
    public MoveType getPlayerMove(){
        return playerMove.getMoveType();
    }
}
