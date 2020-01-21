package com.example.CricketGameTrial.domain;

// Player class will contain details of each player
// Right now basic details have been added, other variables like height, weight can be added if needed
// Also its subclasses can be created like Batsman, Bowler, AllRounder and they can contain variables like
// wicketsTaken, runsScored, battingAverage, maxBowlingSpeed, etc. if needed
public class Player {
    private final String name;
    private final int jerseyNumber;
    private final int age;
    private int runsScored, ballsPlayed, ballsBowled;
    private int wicketsTaken, runsGiven;
    private int numOfBoundaries, numOfSixes, numOfMaidenOvers;
    private double economy, strike_rate;

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getAge() {
        return age;
    }

    public int getRunsScored(){
        return runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void addRunsScored(int runs) {
        runsScored += runs;
    }

    public void addWicketsTaken() {
        wicketsTaken++;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void addBallsPlayed() {
        ballsPlayed++;
    }

    public void addNumOfBoundaries() {
        this.numOfBoundaries++;
    }

    public int getNumOfBoundaries() {
        return numOfBoundaries;
    }

    public void addNumOfSixes() {
        this.numOfSixes++;
    }

    public int getNumOfSixes() {
        return numOfSixes;
    }

    public int getNumOfMaidenOvers() {
        return numOfMaidenOvers;
    }

    public void addNumOfMaidenOvers() {
        numOfMaidenOvers++;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void addBallsBowled() {
        this.ballsBowled++;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    public void addRunsGiven(int runsGiven) {
        this.runsGiven += runsGiven;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public double getStrike_rate() {
        return strike_rate;
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
