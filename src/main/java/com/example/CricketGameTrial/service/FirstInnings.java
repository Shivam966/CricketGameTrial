package com.example.CricketGameTrial.service;

//FirstInnings is a Innings and thus it extends Innings.
class FirstInnings extends Innings {

    // In start method of first innings there will be no target to achieve.
    @Override
    public void start(int numOfOvers) {
        int wickets = 0, runs = 0;  // wickets and runs represent usual convention
        //int balls = numOfOvers*6;  // balls represents number of balls to be played
        int count = 0, ran; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        this.setBattingPlayer(this.getBattingTeam().getPlayer(0));
        this.setNonStrikerBattingPlayer(this.getBattingTeam().getPlayer(1));
        this.setBowlingPlayer(this.getBowlingTeam().getPlayer(index));

        while(count<numOfOvers && wickets<10) {
            int c = 0;
            this.getOvers().add(new Over());
            int runsInOver = 0;
            while(c < 6 && wickets<10) {
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
                //this.getBattingTeam().setRuns(runs);           // Runs and wickets can be updated loop wise also
                //this.getBattingTeam().setWickets(wickets);     // if needed for further enhancement
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
