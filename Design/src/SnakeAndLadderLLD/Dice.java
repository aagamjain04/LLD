package SnakeAndLadderLLD;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private int diceCount;

    public Dice(int diceCount) {
        this.diceCount = diceCount;

    }

    public int rollDice(){

        return 1+ThreadLocalRandom.current().nextInt(6*diceCount);

    }
}
