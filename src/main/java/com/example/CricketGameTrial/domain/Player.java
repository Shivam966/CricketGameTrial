package com.example.CricketGameTrial.domain;

// Player class will contain details of each player
// Right now basic details have been added, other variables like height, weight can be added if needed
// Also its subclasses can be created like Batsman, Bowler, AllRounder and they can contain variables like
// wicketsTaken, runsScored, battingAverage, maxBowlingSpeed, etc. if needed
public class Player {
    private final String name;
    private final int jerseyNumber;
    private final int age;

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getAge() {
        return age;
    }

    public Player(String name, int jerseyNumber, int age) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
    }
}
