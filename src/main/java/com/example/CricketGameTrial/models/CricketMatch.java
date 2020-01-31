package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CricketMatch {
    /*
    Match will have matchID that will be unique for each match.
    Match class will have 2 teams because each match comprises of 2 teams but vice versa is not true, thus it is
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
}
