package CricbuzzLLD.Player;

import java.util.*;

public class PlayerBowlingController {

    Deque<PlayerDetails> bowlersQueue;
    Map<PlayerDetails,Integer> bowlerToBallsBowled;
    PlayerDetails currentBowler;

    public PlayerBowlingController(List<PlayerDetails> bowlers) {
        this.bowlersQueue = new LinkedList<>();
        this.bowlerToBallsBowled = new HashMap<>();
        this.currentBowler = null;
        for(PlayerDetails bowler : bowlers) {
            bowlersQueue.addLast(bowler);
            bowlerToBallsBowled.put(bowler, 0);
        }
    }

    public void getNextBowler(int maxOverCountPerBowler) {
        PlayerDetails playerDetails = bowlersQueue.poll();
        if(bowlerToBallsBowled.get(playerDetails)+1== maxOverCountPerBowler){
            currentBowler = playerDetails;
        }else if(bowlerToBallsBowled.get(playerDetails)< maxOverCountPerBowler) {
            currentBowler = playerDetails;
            bowlersQueue.addLast(playerDetails);
            bowlerToBallsBowled.put(playerDetails, bowlerToBallsBowled.get(playerDetails) + 1);
        }

    }

    public PlayerDetails getCurrentBowler() {
        return currentBowler;
    }


}
