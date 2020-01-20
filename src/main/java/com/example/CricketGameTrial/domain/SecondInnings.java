package com.example.CricketGameTrial.domain;

// SecondInnings is a Innings and thus it extends Innings.
public class SecondInnings extends Innings {

    // In start method of second innings target will be taken into consideration.
    @Override
    public int start(int numOfOvers) {
        int runs = 0, wickets = 0, target = this.getBowlingTeam().getRuns(); // target is set as bowling teams score
        int count = 0, balls = numOfOvers*6, ran;                            // to win the match batting team should
                                                                             // score higher than target
        while(count<balls && wickets<10 && runs<=target) {
            ran = (int) (Math.random() * 8);

            // Here "7" is considered as wicket
            if (ran == 7) {
                wickets++;
            } else {
                runs += ran;
            }
            //this.getBattingTeam().setRuns(runs);
            //this.getBattingTeam().setWickets(wickets);
            count++;
        }
        this.getBattingTeam().setRuns(runs);
        this.getBattingTeam().setWickets(wickets);
        return count;
    }
}
