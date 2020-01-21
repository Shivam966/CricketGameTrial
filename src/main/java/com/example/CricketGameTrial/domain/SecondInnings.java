package com.example.CricketGameTrial.domain;

// SecondInnings is a Innings and thus it extends Innings.
public class SecondInnings extends Innings {

    // In start method of second innings target will be taken into consideration.
    @Override
    public int start(int numOfOvers) {
        int runs = 0, wickets = 0, target = this.getBowlingTeam().getRuns(); // target is set as bowling teams score
        int count = 0, ran;                            // to win the match batting team should
                                                                             // score higher than target
        this.setBattingPlayer(this.getBattingTeam().getPlayer(0));
        this.setNonStrikerBattingPlayer(this.getBattingTeam().getPlayer(1));

        while(count<numOfOvers && wickets<10 && runs<=target) {

            int c = 0;
            this.getOvers().add(new Over());
            while(c<6 && wickets<10 && runs<=target) {
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
                //this.getBattingTeam().setRuns(runs);
                //this.getBattingTeam().setWickets(wickets);
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
