package com.example.CricketGameTrial.domain;

public class Team {
    //"name" should be final because once a team gets a name it doesn't make sense to change that name
    private final String name;

    // Each team will have their runs and wickets when they are batting
    private int runs;
    private int wickets;

    // Assuming right now that each team has only 11 players and no substitutes
    Player[] players = new Player[11];

    public String getName() {
        return name;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public Team(String name) {
        this.name = name;
    }
}
