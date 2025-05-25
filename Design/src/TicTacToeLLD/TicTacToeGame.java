package TicTacToeLLD;

import TicTacToeLLD.Models.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicTacToeGame {

    Queue<Player> players;
    Board board;
    final int boardSize = 3;

    public void initializeGame(){
        //creating two players
        Player player1 = new Player("Player1",new PlayerMoveX());
        Player player2 = new Player("Player2",new PlayerMoveO());

        players = new LinkedList<>();
        players.add(player1);
        players.add(player2);

        //initialize board
        board = new Board(boardSize);


    }
    public void startGame(){

        boolean gameOn = true;
        while(gameOn){

            // 1.print board
            // 2.check for free space
            // 3.take input from user
            // 4.mark the move on board
            // 5.check winner

            board.printBoard();

            Player playerTurn = players.peek();


            if(board.isOver()){
                gameOn = false;
                continue;
            }
            System.out.print(playerTurn.getPlayerName() + " Enter row,column: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            String[] values =  input.split(",");
            int row = Integer.parseInt(values[0]);
            int col = Integer.parseInt(values[1]);

            if(board.placeMove(row,col,playerTurn)){
               Player currPlayer = players.peek();
               players.remove();
               players.add(currPlayer);
            }else{
                System.out.println("Incorrect position chosen please try again..");
            }

            if(isWinner(row,col,playerTurn.getPlayerMove())){
                board.printBoard();
                System.out.println("Winner is " + playerTurn.getPlayerName());
                return;
            }


        }
        System.out.println("Game tied");

    }

    public boolean isWinner(int row,int col,MoveType moveType){

        boolean rowCheck = true;
        boolean colCheck = true;
        boolean diagonalCheck1 = true;
        boolean diagonalCheck2 = true;

        MoveType[][] board1 = board.getBoard();

        // check row
        for(int i=0; i<boardSize; i++){
            if(board1[row][i]==null || board1[row][i]!=moveType){
                rowCheck = false;
            }
        }

        // check col
        for(int i=0; i<boardSize; i++){
            if(board1[i][col]==null || board1[i][col]!=moveType){
                colCheck = false;
            }
        }

        // diagonal1 check
        for(int i = 0; i < boardSize; i++){
            if(board1[i][i]==null || board1[i][i]!=moveType){
                diagonalCheck1 = false;
            }
        }

        // diagonal2 check
        for(int i = 0; i < boardSize; i++){
            if(board1[i][boardSize-i-1]==null || board1[i][boardSize-i-1]!=moveType){
                diagonalCheck2 = false;
            }
        }


        return (rowCheck || colCheck || diagonalCheck1 || diagonalCheck2);
    }

}
