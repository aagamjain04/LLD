package TicTacToeLLD.Models;

public class Board {
    int size;
    MoveType[][] board;

    public Board(int size) {
        this.size = size;
        board = new MoveType[size][size];
    }

    public Boolean placeMove(int row,int col,Player player){
        if(row>=size || col>=size || board[row][col]!=null)
            return false;

        board[row][col] = player.playerMove.getMoveType();
        return true;
    }

    public void printBoard(){

        for (MoveType[] moveTypes : board) {
            for (int j = 0; j < board.length; j++) {
                if(moveTypes[j]!=null) {
                    System.out.print(" " + moveTypes[j] + " |");
                }
                else{
                    System.out.print("   |");
                }
            }
            System.out.print("\n");
        }

    }

    public Boolean isOver(){

        for(MoveType[] moveTypes : board){
            for(int j = 0; j < board.length; j++){
                if(moveTypes[j]==null){
                    return false;
                }
            }
        }
        return true;

    }

    public MoveType[][] getBoard(){
        return board;
    }

}
