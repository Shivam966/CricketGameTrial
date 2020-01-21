package com.example.CricketGameTrial.domain;

public class Over {
    private int runsScored;       // runScored stores runs scored in that over
    private int wicketsTaken;     // wicketsTaken stores wickets taken in that over
    private int ballsPlayed;      // ballsPlayed stores number of balls played in that over

    public void addRunsScored(int runsScored) {
        this.runsScored += runsScored;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void addWicketsTaken() {
        this.wicketsTaken++;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void addBallsPlayed() {
        this.ballsPlayed++;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }
}
