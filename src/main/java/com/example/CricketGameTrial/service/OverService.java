package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.models.Innings;

abstract class OverService {
    public static int start(Innings innings) {
        int ran;
        int runsInOver = 0;
        int c = 0;
        while(c < 6 && innings.getWickets()<10) {
            ran = (int) (Math.random() * 8);

            // Here "7" is considered as wicket
            innings.getBattingPlayer().addBallsPlayed();
            innings.getBowlingPlayer().addBallsBowled();
            if (ran == 7) {
                innings.setWickets(innings.getWickets()+1);
                innings.setBattingPlayer(innings.getBattingTeam().getPlayers().get(innings.getWickets()));
                innings.getBowlingPlayer().addWicketsTaken();
            } else {
                innings.setRuns(innings.getRuns()+ran);
                runsInOver += ran;
                innings.getBattingPlayer().addRunsScored(ran);
                innings.getBowlingPlayer().addRunsGiven(ran);
                if(ran==4) innings.getBattingPlayer().addNumOfFours();
                if(ran==6) innings.getBattingPlayer().addNumOfSixes();
            }
            if(ran<7) innings.getOvers().get(innings.getOvers().size()-1).getBalls().add(Character.forDigit(ran,
                    10));
            else innings.getOvers().get(innings.getOvers().size()-1).getBalls().add('W');
            c++;
        }
        return runsInOver;
    }

    public static int start(Innings innings, int target) {
        int ran;
        int runsInOver = 0;
        int c = 0;
        while(c < 6 && innings.getWickets()<10 && innings.getRuns()<=target) {
            ran = (int) (Math.random() * 8);

            // Here "7" is considered as wicket
            innings.getBattingPlayer().addBallsPlayed();
            innings.getBowlingPlayer().addBallsBowled();
            if (ran == 7) {
                innings.setWickets(innings.getWickets()+1);
                innings.setBattingPlayer(innings.getBattingTeam().getPlayers().get(innings.getWickets()));
                innings.getBowlingPlayer().addWicketsTaken();
            } else {
                innings.setRuns(innings.getRuns()+ran);
                runsInOver += ran;
                innings.getBattingPlayer().addRunsScored(ran);
                innings.getBowlingPlayer().addRunsGiven(ran);
                if(ran==4) innings.getBattingPlayer().addNumOfFours();
                if(ran==6) innings.getBattingPlayer().addNumOfSixes();
            }
            if(ran<7) innings.getOvers().get(innings.getOvers().size()-1).getBalls().add(Character.forDigit(ran,
                    10));
            else innings.getOvers().get(innings.getOvers().size()-1).getBalls().add('W');
            c++;
        }
        return runsInOver;
    }
}
