package CricbuzzLLD.Innings;

import CricbuzzLLD.Player.PlayerDetails;

import java.util.List;

public class OverDetails {
    int overNumber;
    List<BallDetails> balls;
    PlayerDetails bowledBy;
    int extraBalls;

    public OverDetails(int overNumber,PlayerDetails bowledBy){
        this.overNumber=overNumber;
        this.bowledBy=bowledBy;
        balls = new java.util.ArrayList<>();
        extraBalls = 0;
    }

    public boolean startOver(Team battingTeam,Team bowlingTeam,int runsToWin){
        int ballCount = 1;
        while(ballCount<=6){

            BallDetails ball = new BallDetails(ballCount);
            ball.startBallDelivery(battingTeam,bowlingTeam,this);
            if(ball.ballType == BallType.NORMAL){
                balls.add(ball);
                ballCount++;
                if(ball.wicket!=null){
                    battingTeam.chooseNextBatsman();
                }

                if( runsToWin!=-1 && battingTeam.getTotalRuns()>=runsToWin){
                    battingTeam.isWinner = true;
                    return true;
                }
            }else{
                extraBalls++;
            }

        }
        return false;
    }
}
