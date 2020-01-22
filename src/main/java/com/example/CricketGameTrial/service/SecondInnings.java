package com.example.CricketGameTrial.service;

// SecondInnings is a Innings and thus it extends Innings.
class SecondInnings extends Innings {

    // In start method of second innings target will be taken into consideration.
    @Override
    public void start(int numOfOvers) {
        int runs = 0, wickets = 0, target = this.getBowlingTeam().getRuns(); // target is set as bowling teams score
        int count = 0, ran;                            // to win the match batting team should
                                                                             // score higher than target
        int index = 6;
        this.setBattingPlayer(this.getBattingTeam().getPlayer(0));
        this.setNonStrikerBattingPlayer(this.getBattingTeam().getPlayer(1));
        this.setBowlingPlayer(this.getBowlingTeam().getPlayer(index));

        while(count<numOfOvers && wickets<10 && runs<=target) {

            int c = 0;
            this.getOvers().add(new Over());
            int runsInOver = 0;
            while(c<6 && wickets<10 && runs<=target) {
                ran = (int) (Math.random() * 8);

                // Here "7" is considered as wicket
                this.getBattingPlayer().addBallsPlayed();
                this.getBowlingPlayer().addBallsBowled();
                if (ran == 7) {
                    wickets++;
                    this.setBattingPlayer(this.getBattingTeam().getPlayer(wickets));
                    this.getBowlingPlayer().addWicketsTaken();
                } else {
                    runs += ran;
                    runsInOver += ran;
                    this.getBattingPlayer().addRunsScored(ran);
                    this.getBowlingPlayer().addRunsGiven(ran);
                    if(ran==4) this.getBattingPlayer().addNumOfBoundaries();
                    if(ran==6) this.getBattingPlayer().addNumOfSixes();
                }
                if(ran<7) this.getOvers().get(this.getOvers().size()-1).getBalls().add(Character.forDigit(ran,
                        10));
                else this.getOvers().get(this.getOvers().size()-1).getBalls().add('W');
                //this.getBattingTeam().setRuns(runs);
                //this.getBattingTeam().setWickets(wickets);
                c++;
            }
            if(this.getOvers().get(this.getOvers().size()-1).getBalls().size()==6 && runsInOver==0)
                this.getBowlingPlayer().addNumOfMaidenOvers();

            if(index==10) index = 6;
            else index++;
            this.setBowlingPlayer(this.getBowlingTeam().getPlayer(index));

            Player temp = this.getBattingPlayer();
            this.setBattingPlayer(this.getNonStrikerBattingPlayer());
            this.setNonStrikerBattingPlayer(temp);
            count++;
        }
        this.getBattingTeam().setRuns(runs);
        this.getBattingTeam().setWickets(wickets);
    }
}
