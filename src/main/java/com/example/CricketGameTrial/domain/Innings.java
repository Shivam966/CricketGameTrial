package com.example.CricketGameTrial.domain;

import java.util.ArrayList;

/* Innings class is kept abstract because it does not make sense to make instance of innings class, it's purpose is to
   impose protocol for its subclasses
   It will have 2 teams - batting team and bowling team that will refer to original team objects
   During toss, setter of batting team and bowling team will be called
   start() method has been made abstract because first innings and seconds innings will have different implementations
   of start, latter will have target
*/
public abstract class Innings {

    private Team battingTeam, bowlingTeam;
    private Player battingPlayer, nonStrikerBattingPlayer, bowlingPlayer;
    private ArrayList<Over> overs = new ArrayList<>();

    // start method takes number of overs to be played as an argument because innings can only start by mentioning
    // number of overs to be played in it and returns number of overs played by the batting team
    public abstract void start(int n);

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public void setBattingPlayer(Player battingPlayer) {
        this.battingPlayer = battingPlayer;
    }

    public Player getBattingPlayer() {
        return battingPlayer;
    }

    public void setNonStrikerBattingPlayer(Player player) {
        this.nonStrikerBattingPlayer = player;
    }

    public Player getNonStrikerBattingPlayer() {
        return nonStrikerBattingPlayer;
    }

    public Player getBowlingPlayer() {
        return bowlingPlayer;
    }

    public void setBowlingPlayer(Player bowlingPlayer) {
        this.bowlingPlayer = bowlingPlayer;
    }

    public ArrayList<Over> getOvers() {
        return overs;
    }

    public void setOvers(ArrayList<Over> overs) {
        this.overs = overs;
    }

}
