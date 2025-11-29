package CricbuzzLLD.ScoreUpdater;

import CricbuzzLLD.Innings.BallDetails;
import CricbuzzLLD.Innings.RunType;

public class BattingScoreUpdater implements ScoreUpdateObserver{

    @Override
    public void update(BallDetails ballDetails) {
        int run = 0;
        if(RunType.ONE == ballDetails.runType){
            run = 1;
        } else if (RunType.TWO == ballDetails.runType) {
            run = 2;
        } else if (RunType.FOUR == ballDetails.runType) {
            run = 4;
            ballDetails.playedBy.battingScoreCard.totalFours++;
        } else if (RunType.SIX == ballDetails.runType) {
            run = 6;
            ballDetails.playedBy.battingScoreCard.totalSix++;
        }

        ballDetails.playedBy.battingScoreCard.totalBallsPlayed +=1;
        ballDetails.playedBy.battingScoreCard.totalRuns+=run;

        if(ballDetails.wicket!=null){
            ballDetails.playedBy.battingScoreCard.wicketDetails = ballDetails.wicket;
        }
    }
}
