package com.example.CricketGameTrial.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/* Innings class is kept abstract because it does not make sense to make instance of innings class, it's purpose is to
   impose protocol for its subclasses
   It will have 2 teams - batting team and bowling team that will refer to original team objects
   During toss, setter of batting team and bowling team will be called
   start() method has been made abstract because first innings and seconds innings will have different implementations
   of start, latter will have target
*/
@Getter
@Setter
@JsonIgnoreProperties ({"battingTeam","bowlingTeam","battingPlayer","nonStrikerBattingPlayer","bowlingPlayer"})
class Innings {

    private CricketTeam battingTeam, bowlingTeam;
    private CricketPlayer battingPlayer, nonStrikerBattingPlayer, bowlingPlayer;
    private ArrayList<Over> overs = new ArrayList<>();

    // start method takes number of overs to be played as an argument because innings can only start by mentioning
    // number of overs to be played in it and returns number of overs played by the batting team
    public void start(int numOfOvers) {
        int wickets = 0, runs = 0;  // wickets and runs represent usual convention
        // balls represents number of balls to be played
        int count = 0, ran; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        battingPlayer = battingTeam.getPlayer(0);
        nonStrikerBattingPlayer = battingTeam.getPlayer(1);
        bowlingPlayer = bowlingTeam.getPlayer(index);

        while(count<numOfOvers && wickets<10) {
            int c = 0;
            overs.add(new Over());
            int runsInOver = 0;
            while(c < 6 && wickets<10) {
                ran = (int) (Math.random() * 8);

                // Here "7" is considered as wicket
                battingPlayer.addBallsPlayed();
                bowlingPlayer.addBallsBowled();
                if (ran == 7) {
                    wickets++;
                    battingPlayer = battingTeam.getPlayer(wickets);
                    bowlingPlayer.addWicketsTaken();
                } else {
                    runs += ran;
                    runsInOver += ran;
                    battingPlayer.addRunsScored(ran);
                    bowlingPlayer.addRunsGiven(ran);
                    if(ran==4) battingPlayer.addNumOfFours();
                    if(ran==6) battingPlayer.addNumOfSixes();
                }
                if(ran<7) overs.get(overs.size()-1).getBalls().add(Character.forDigit(ran,
                        10));
                else overs.get(overs.size()-1).getBalls().add('W');
                //this.getBattingTeam().setRuns(runs);           // Runs and wickets can be updated loop wise also
                //this.getBattingTeam().setWickets(wickets);     // if needed for further enhancement
                c++;
            }
            if(overs.get(overs.size()-1).getBalls().size()==6 && runsInOver==0)
                this.getBowlingPlayer().addNumOfMaidenOvers();

            if(index==10) index = 6;
            else index++;
            bowlingPlayer = bowlingTeam.getPlayer(index);

            CricketPlayer temp = battingPlayer;
            battingPlayer = nonStrikerBattingPlayer;
            nonStrikerBattingPlayer = temp;
            count++;
        }
        battingTeam.setRuns(runs);
        battingTeam.setWickets(wickets);
    }

    public void start(int numOfOvers, int target) {
        int wickets = 0, runs = 0;  // wickets and runs represent usual convention
        // balls represents number of balls to be played
        int count = 0, ran; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        battingPlayer = battingTeam.getPlayer(0);
        nonStrikerBattingPlayer = battingTeam.getPlayer(1);
        bowlingPlayer = bowlingTeam.getPlayer(index);

        while(count<numOfOvers && wickets<10 && runs<=target) {
            int c = 0;
            overs.add(new Over());
            int runsInOver = 0;
            while(c < 6 && wickets<10 && runs<=target) {
                ran = (int) (Math.random() * 8);

                // Here "7" is considered as wicket
                battingPlayer.addBallsPlayed();
                bowlingPlayer.addBallsBowled();
                if (ran == 7) {
                    wickets++;
                    battingPlayer = battingTeam.getPlayer(wickets);
                    bowlingPlayer.addWicketsTaken();
                } else {
                    runs += ran;
                    runsInOver += ran;
                    battingPlayer.addRunsScored(ran);
                    bowlingPlayer.addRunsGiven(ran);
                    if(ran==4) battingPlayer.addNumOfFours();
                    if(ran==6) battingPlayer.addNumOfSixes();
                }
                if(ran<7) overs.get(overs.size()-1).getBalls().add(Character.forDigit(ran,
                        10));
                else overs.get(overs.size()-1).getBalls().add('W');
                //this.getBattingTeam().setRuns(runs);           // Runs and wickets can be updated loop wise also
                //this.getBattingTeam().setWickets(wickets);     // if needed for further enhancement
                c++;
            }
            if(overs.get(overs.size()-1).getBalls().size()==6 && runsInOver==0)
                this.getBowlingPlayer().addNumOfMaidenOvers();

            if(index==10) index = 6;
            else index++;
            bowlingPlayer = bowlingTeam.getPlayer(index);

            CricketPlayer temp = battingPlayer;
            battingPlayer = nonStrikerBattingPlayer;
            nonStrikerBattingPlayer = temp;
            count++;
        }
        battingTeam.setRuns(runs);
        battingTeam.setWickets(wickets);
    }
}
