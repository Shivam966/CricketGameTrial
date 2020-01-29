package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {

    private int runsScored, ballsPlayed, ballsBowled, wicketsTaken, runsGiven;
    private int numOfFours, numOfSixes, numOfMaidenOvers;
    private float playerEconomy, playerStrike_rate;

    public void addRunsScored(int runs) {
        runsScored+=runs;
    }

    public void addWicketsTaken() {
        wicketsTaken++;
    }

    public void addBallsPlayed() {
        ballsPlayed++;
    }

    public void addNumOfFours() {
        numOfFours++;
    }

    public void addNumOfSixes() {
        numOfSixes++;
    }

    public void addNumOfMaidenOvers() {
        numOfMaidenOvers++;
    }

    public void addBallsBowled() {
        ballsBowled++;
    }

    public void addRunsGiven(int runsGiven) {
        this.runsGiven+=runsGiven;
    }

}
