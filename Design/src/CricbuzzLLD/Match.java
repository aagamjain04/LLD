package CricbuzzLLD;

import CricbuzzLLD.Innings.InningDetails;
import CricbuzzLLD.Innings.Team;

import java.util.Date;

public class Match {

    Team teamA;
    Team teamB;
    Date matchDate;
    String venue;
    Team tossWinner;
    InningDetails[] innings;
    MatchType matchType;

    public Match(Team teamA, Team teamB, Team tossWinner, String venue, MatchType matchType) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.tossWinner = tossWinner;
        this.venue = venue;
        this.matchType = matchType;
        this.innings = new InningDetails[2];
    }

    public void startMatch() {

        //1. Toss
        tossWinner = toss(teamA, teamB);

        for(int inning = 1; inning <=2 ; inning++){
            InningDetails inningDetails;
            Team battingTeam;
            Team bowlingTeam;

            //assuming here toss winner always choose to bat first
            boolean isChasing = false;
            if(inning==1){
                battingTeam = tossWinner;
                bowlingTeam = (battingTeam == teamA) ? teamB : teamA;
                inningDetails = new InningDetails(battingTeam, bowlingTeam, matchType);
                inningDetails.startInning(-1);
            }else{
                bowlingTeam = tossWinner;
                battingTeam = (bowlingTeam == teamA) ? teamB : teamA;
                inningDetails = new InningDetails(battingTeam, bowlingTeam, matchType);
                inningDetails.startInning(innings[0].getTotalRuns()+1);
                if(bowlingTeam.getTotalRuns() > battingTeam.getTotalRuns()){
                    bowlingTeam.isWinner = true;
                }
            }
            innings[inning-1] = inningDetails;

            // print inning details
            System.out.println();
            System.out.println("INNING " + inning + " -- total runs: " + inningDetails.getTotalRuns());
            System.out.println("---Batting Score Card of " + battingTeam.getTeamName() + "---");

            battingTeam.printBattingScoreCard();

            System.out.println();
            System.out.println("---Bowling Score Card of " + bowlingTeam.getTeamName() + "---");

            bowlingTeam.printBowlingScoreCard();
        }
    }


    private Team toss(Team teamA, Team teamB) {

        double val = Math.random();
        if(val<0.5){
            return teamA;
        }else {
            return teamB;
        }
    }
}
