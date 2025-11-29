package CricbuzzLLD.Innings;

import CricbuzzLLD.Player.PlayerBattingController;
import CricbuzzLLD.Player.PlayerBowlingController;
import CricbuzzLLD.Player.PlayerDetails;


import java.util.List;
import java.util.Queue;

public class Team {
    public String teamName;
    public Queue<PlayerDetails> playing11;
    public PlayerBattingController playerBattingContorller;
    public PlayerBowlingController playerBowlingController;
    public boolean isWinner;

    public Team(String teamName, Queue<PlayerDetails> playing11, List<PlayerDetails> bowlers) {
        this.teamName = teamName;
        this.playing11 = playing11;
        this.playerBattingContorller = new PlayerBattingController(playing11);
        this.playerBowlingController = new PlayerBowlingController(bowlers);
        this.isWinner = false;
    }

    public String getTeamName() {
        return teamName;
    }
    public void chooseNextBatsman() {
        playerBattingContorller.getNextPlayer();
    }
    public void chooseNextBowler(int maxOverCountPerBowler) {
        playerBowlingController.getNextBowler(maxOverCountPerBowler);
    }
    public PlayerDetails getStriker() {
        return playerBattingContorller.getStriker();
    }
    public void setStriker(PlayerDetails striker) {
        playerBattingContorller.setStriker(striker);
    }
    public PlayerDetails getNonStriker() {
        return playerBattingContorller.getNonStriker();
    }
    public void setNonStriker(PlayerDetails nonStriker) {
        playerBattingContorller.setNonStriker(nonStriker);
    }
    public PlayerDetails getCurrentBowler() {
        return playerBowlingController.getCurrentBowler();
    }

    public void printBattingScoreCard(){
        for(PlayerDetails playerDetails : playing11){
            playerDetails.printBattingScoreCard();
        }
    }
    public void printBowlingScoreCard(){
        for(PlayerDetails playerDetails : playing11){
            if(playerDetails.bowlingScoreCard.totalOversCount > 0){
                playerDetails.printBowlingScoreCard();
            }

        }
    }

    public int getTotalRuns() {
        int totalRuns = 0;
        for(PlayerDetails playerDetails : playing11){
            totalRuns += playerDetails.battingScoreCard.totalRuns;
        }
        return totalRuns;
    }
}
