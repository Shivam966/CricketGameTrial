package com.example.CricketGameTrial.models;

import com.example.CricketGameTrial.service.CricketTeamService;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class CricketMatch {
    /*
    Match class will have 2 team objects because each match comprises of 2 teams but vice versa is not true, thus it is
    an Aggregation relationship.
    In the same way it will have 2 innings-first and second, 1 numOfOvers variable to determine how many overs each
    inning will play and 1 tossWinningTeam variable that will store the toss winning team.
    */
    private int numOfOvers;
    private String tossWinningTeam;
    private String choseTo;
    private CricketTeam teamA,teamB;
    private Innings firstInnings;
    private Innings secondInnings;
    private String result;

    public CricketMatch(int numOfOvers, String team1, String team2) {
        this.numOfOvers = numOfOvers;
        firstInnings = new Innings();
        secondInnings = new Innings();
        teamA = CricketTeamService.getTeam(team1);
        teamB = CricketTeamService.getTeam(team2);
    }

    // startMatch method is the actual method that will start the match, it will call toss(), first.start(int) and
    // second.start(int)
}
