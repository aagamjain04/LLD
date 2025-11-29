package CricbuzzLLD.Innings;

import CricbuzzLLD.MatchType;
import CricbuzzLLD.Player.PlayerDetails;

import java.util.ArrayList;
import java.util.List;

public class InningDetails {
    Team battingTeam;
    Team bowlingTeam;
    MatchType matchType;
    List<OverDetails> overs;

    public InningDetails(Team battingTeam, Team bowlingTeam, MatchType matchType) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.matchType = matchType;
        this.overs = new ArrayList<>();
    }

    public void startInning(int runToWin){

        // set batting players
        battingTeam.chooseNextBatsman();

        int noOfOvers = matchType.noOfOvers();
        for(int i=1;i<=noOfOvers;i++){
            // choose bowler
            bowlingTeam.chooseNextBowler(matchType.maxOverCountBowlers());
            OverDetails over = new OverDetails(i, bowlingTeam.getCurrentBowler());
            overs.add(over);
            try {
                boolean won = over.startOver(battingTeam,bowlingTeam,runToWin);
                if(won){
                    break;
                }
            } catch (Exception e) {
                break;
            }

            // swap striker and non striker
            PlayerDetails temp = battingTeam.getStriker();
            battingTeam.setStriker(battingTeam.getNonStriker());
            battingTeam.setNonStriker(temp);
        }
    }

    public int getTotalRuns(){
        return battingTeam.getTotalRuns();
    }

}
