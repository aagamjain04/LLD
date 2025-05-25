package TicTacToeLLD.Models;

public class PlayerMove {

    MoveType moveType;

    PlayerMove(MoveType moveType){
        this.moveType = moveType;
    }

    public MoveType getMoveType(){
        return moveType;
    }

}
