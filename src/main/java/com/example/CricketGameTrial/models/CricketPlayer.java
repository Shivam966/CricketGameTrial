package com.example.CricketGameTrial.models;

// Player class will contain details of each player
// Right now basic details have been added, other variables like height, weight can be added if needed
// Also its subclasses can be created like Batsman, Bowler, AllRounder and they can contain variables like
// wicketsTaken, runsScored, battingAverage, maxBowlingSpeed, etc. if needed

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CricketPlayer {
    private final String name, team;
    private final int jerseyNumber, age;
    private int runsScored, ballsPlayed, ballsBowled, wicketsTaken, runsGiven;
    private int numOfFours, numOfSixes, numOfMaidenOvers;
    private float economy, strike_rate;

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

    public CricketPlayer(String name, String team, int jerseyNumber, int age) {
        this.name = name;
        this.team = team;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
    }
}
