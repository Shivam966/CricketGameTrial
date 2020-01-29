package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CricketMatch {
    /*
    Match class will have 2 team objects because each match comprises of 2 teams but vice versa is not true, thus it is
    an Aggregation relationship.
    In the same way it will have 2 innings-first and second, 1 numOfOvers variable to determine how many overs each
    inning will play and 1 tossWinningTeam variable that will store the toss winning team.
    */
    private int matchID;
    private int numOfOvers;
    private String tossWinningTeam;
    private String choseTo;
    private String teamA,teamB;
    private Innings firstInnings;
    private Innings secondInnings;
    private String result;

    public CricketMatch(int numOfOvers, String team1, String team2) {
        matchID = this.hashCode();
        this.numOfOvers = numOfOvers;
        firstInnings = new Innings();
        secondInnings = new Innings();
        teamA = team1;
        teamB = team2;
    }

    // startMatch method is the actual method that will start the match, it will call toss(), first.start(int) and
    // second.start(int)
}
