package com.example.CricketGameTrial.service;

// Player class will contain details of each player
// Right now basic details have been added, other variables like height, weight can be added if needed
// Also its subclasses can be created like Batsman, Bowler, AllRounder and they can contain variables like
// wicketsTaken, runsScored, battingAverage, maxBowlingSpeed, etc. if needed

import lombok.Getter;

@Getter
class Player {
    private final String name;
    private final int jerseyNumber, age;
    private int runsScored, ballsPlayed, ballsBowled, wicketsTaken, runsGiven;
    private int numOfFours, numOfSixes, numOfMaidenOvers;
    private double economy, strike_rate;

    public void addRunsScored(int runs) {
        runsScored += runs;
    }

    public void addWicketsTaken() {
        wicketsTaken++;
    }

    public void addBallsPlayed() {
        ballsPlayed++;
    }

    public void addNumOfFours() {
        this.numOfFours++;
    }

    public void addNumOfSixes() {
        this.numOfSixes++;
    }

    public void addNumOfMaidenOvers() {
        numOfMaidenOvers++;
    }

    public void addBallsBowled() {
        this.ballsBowled++;
    }

    public void addRunsGiven(int runsGiven) {
        this.runsGiven += runsGiven;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public void setStrike_rate(double strike_rate) {
        this.strike_rate = strike_rate;
    }

    public Player(String name, int jerseyNumber, int age) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
    }
}
