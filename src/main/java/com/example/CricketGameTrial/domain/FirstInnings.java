package com.example.CricketGameTrial.domain;

//FirstInnings is a Innings and thus it extends Innings.
public class FirstInnings extends Innings {

    // In start method of first innings there will be no target to achieve.
    @Override
    public int start(int numOfOvers) {
        int wickets = 0, runs = 0;  // wickets and runs represent usual convention
        //int balls = numOfOvers*6;  // balls represents number of balls to be played
        int count = 0, ran; // count is counter for while loop to check numOfOvers and ran will be used for random
        this.setBattingPlayer(this.getBattingTeam().getPlayer(0));
        this.setNonStrikerBattingPlayer(this.getBattingTeam().getPlayer(1));

        while(count<numOfOvers && wickets<10) {
            int c = 0;
            this.getOvers().add(new Over());
            while(c < 6 && wickets<10) {
                ran = (int) (Math.random() * 8);

                // Here "7" is considered as wicket
                this.getBattingPlayer().addBallsPlayed();
                if (ran == 7) {
                    wickets++;
                    this.setBattingPlayer(this.getBattingTeam().getPlayer(wickets));
                    this.getOvers().get(count).addWicketsTaken();
                } else {
                    runs += ran;
                    this.getBattingPlayer().addRunsScored(ran);
                    this.getOvers().get(count).addRunsScored(ran);
                    if(ran==4) this.getBattingPlayer().addNumOfBoundaries();
                    if(ran==6) this.getBattingPlayer().addNumOfSixes();
                }
                this.getOvers().get(count).addBallsPlayed();
                //this.getBattingTeam().setRuns(runs);           // Runs and wickets can be updated loop wise also
                //this.getBattingTeam().setWickets(wickets);     // if needed for further enhancement
                c++;
            }
            Player temp = this.getBattingPlayer();
            this.setBattingPlayer(this.getNonStrikerBattingPlayer());
            this.setNonStrikerBattingPlayer(temp);
            count++;
        }
        this.getBattingTeam().setRuns(runs);
        this.getBattingTeam().setWickets(wickets);
        return count;
    }
}
