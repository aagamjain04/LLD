package CricbuzzLLD.Innings;


import CricbuzzLLD.Player.PlayerDetails;
import CricbuzzLLD.ScoreUpdater.BattingScoreUpdater;
import CricbuzzLLD.ScoreUpdater.BowlingScoreUpdater;
import CricbuzzLLD.ScoreUpdater.ScoreUpdateObserver;

import java.util.ArrayList;
import java.util.List;

public class BallDetails {
    public int ballNumber;
    public BallType ballType;
    public RunType runType;
    public PlayerDetails playedBy;
    public PlayerDetails bowledBy;
    public Wicket wicket;
    public List<ScoreUpdateObserver> scoreUpdateObserverList = new ArrayList<>();

    public BallDetails(int ballNumber) {
        this.ballNumber = ballNumber;
        scoreUpdateObserverList.add(new BattingScoreUpdater());
        scoreUpdateObserverList.add(new BowlingScoreUpdater());

    }

    public void startBallDelivery(Team battingTeam, Team bowlingTeam, OverDetails over){
        playedBy = battingTeam.getStriker();
        bowledBy = over.bowledBy;
        // Throw BALL and get the BallType, assuming here that ball type is always NORMAL

        ballType = BallType.NORMAL;

        //wicket or no wicket
        if(isWicketFallen()){
            runType = RunType.ZERO;
            wicket = new Wicket(WicketType.BOLD,bowledBy,over,this);
            battingTeam.setStriker(null);
        }else{
            runType = getRunType();
            if(runType == RunType.ONE || runType == RunType.THREE){
                // swap striker and non striker
                PlayerDetails temp = battingTeam.getStriker();
                battingTeam.setStriker(battingTeam.getNonStriker());
                battingTeam.setNonStriker(temp);
            }
        }
        notifyObservers(this);
    }

    private void notifyObservers(BallDetails ballDetails){
        for(ScoreUpdateObserver observer : scoreUpdateObserverList){
            observer.update(ballDetails);
        }
    }

    private boolean isWicketFallen(){
        //assume some logic to decide if wicket has fallen
        if(Math.random() < 0.2){
            return true;
        }else return false;
    }

    private RunType getRunType() {
        double val = Math.random();
        if(val <= 0.2) {
            return RunType.ONE;
        }else if(val >= 0.3 && val <= 0.5) {
            return RunType.TWO;
        }else if(val >= 0.6 && val < 0.8) {
            return RunType.FOUR;
        }else {
            return RunType.SIX;
        }
    }
}
