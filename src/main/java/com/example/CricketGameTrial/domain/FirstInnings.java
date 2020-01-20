package com.example.CricketGameTrial.domain;

//FirstInnings is a Innings and thus it extends Innings.
public class FirstInnings extends Innings {

    // In start method of first innings there will be no target to achieve.
    @Override
    public int start(int numOfOvers) {
        int wickets = 0, runs = 0;  // wickets and runs represent usual convention
        int balls = numOfOvers*6;  // balls represents number of balls to be played
        int count = 0, ran; // count is counter for while loop to check balls and ran will be used for random

        while(count<balls && wickets<10) {
            ran = (int) (Math.random() * 8);

            // Here "7" is considered as wicket
            if (ran == 7) {
                wickets++;
            } else {
                runs += ran;
            }
            //this.getBattingTeam().setRuns(runs);           // Runs and wickets can be updated loop wise also
            //this.getBattingTeam().setWickets(wickets);     // if needed for further enhancement
            count++;
        }
        this.getBattingTeam().setRuns(runs);
        this.getBattingTeam().setWickets(wickets);
        return count;
    }
}
