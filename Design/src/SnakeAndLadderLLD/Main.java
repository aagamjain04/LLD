package SnakeAndLadderLLD;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Jump> snakes = List.of(
                new Jump(99,21),
                new Jump(90,48),
                new Jump(25,5)
        );

        List<Jump> ladders = List.of(
                new Jump(3, 22),
                new Jump(11, 90),
                new Jump(15, 44)
        );

        List<Player> players = List.of(
                new Player("Aagam"),
                new Player("Rahul")
        );

        Board board = new Board(ladders,snakes,100);

        Dice dice = new Dice(1);

        Game game = new Game(board,dice,players);
        game.startGame();


    }
}
