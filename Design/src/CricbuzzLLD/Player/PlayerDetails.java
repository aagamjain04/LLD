package CricbuzzLLD.Player;

import CricbuzzLLD.Score.BattingScoreCard;
import CricbuzzLLD.Score.BowlingScoreCard;

public class PlayerDetails {
    public Person person;
    public PlayerType playerType;
    public BattingScoreCard battingScoreCard;
    public BowlingScoreCard bowlingScoreCard;

    public PlayerDetails(Person person,PlayerType playerType){
        this.person = person;
        this.playerType = playerType;
        battingScoreCard = new BattingScoreCard();
        bowlingScoreCard = new BowlingScoreCard();
    }

    public PlayerType getPlayerType(){
        return this.playerType;
    }

    public void printBattingScoreCard(){

        System.out.println("PlayerName "+person.name +
                " -- totalRuns: " + battingScoreCard.totalRuns +
                " -- totalBallsPlayed: " + battingScoreCard.totalBallsPlayed +
                " -- totalFours: " + battingScoreCard.totalFours +
                " -- totalSix: " + battingScoreCard.totalSix +
                " -- strikeRate: " + battingScoreCard.strikeRate +
                " -- outBy: " + ((battingScoreCard.wicketDetails!=null) ? battingScoreCard.wicketDetails.takenBy.person.name : "Not Out")
                );
    }

    public void printBowlingScoreCard(){

        System.out.println("PlayerName "+person.name +
                " -- totalOversThrown: " + bowlingScoreCard.totalOversCount +
                " -- totalRunsConceded: " + bowlingScoreCard.runsGiven +
                " -- wicketsTaken: " + bowlingScoreCard.wicketsTaken
                );
    }
}
