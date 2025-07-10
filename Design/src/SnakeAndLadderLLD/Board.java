package SnakeAndLadderLLD;

import java.util.List;

public class Board {

    private List<Jump> ladders;
    private List<Jump> snakes;
    private int size;

    public Board(List<Jump> ladders, List<Jump> snakes, int size) {
        this.ladders = ladders;
        this.snakes = snakes;
        this.size = size;
    }

    public List<Jump> getLadders() {
        return ladders;
    }

    public List<Jump> getSnakes() {
        return snakes;
    }

    public int getSize() {
        return size;
    }

    public int getNextPosition(int currentPosition){

        for(Jump snake : snakes){
            if(snake.start == currentPosition)
                return snake.end;
        }

        for(Jump ladder : ladders){
            if(ladder.end == currentPosition)
                return ladder.end;
        }

        return currentPosition;


    }
}
