package com.example.CricketGameTrial.service;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@Getter
public class Match {
    /*
    Match class will have 2 team objects because each match comprises of 2 teams but vice versa is not true, thus it is
    an Aggregation relationship.
    In the same way it will have 2 innings-first and second, 1 numOfOvers variable to determine how many overs each
    inning will play and 1 tossWinningTeam variable that will store the toss winning team.
    */
    private int numOfOvers;
    private String tossWinningTeam;
    private String choseTo = "Bowl";
    private Team teamA,teamB;
    private FirstInnings firstInnings = new FirstInnings();
    private SecondInnings secondInnings = new SecondInnings();
    private String result;

    public void setTeamName(String team1, String team2) {
        teamA = new Team(team1);
        teamB = new Team(team2);
    }

    public void setPlayers(Team t) {
        Player[] players = new Player[11];
        StringBuilder s = new StringBuilder();
        for(int i=0; i < players.length; i++) {
            s.setLength(0);
            s.append("Player ").append((i+1));
            Player p = new Player(s.toString(),i+1,i+1);
            players[i] = p;
        }
        t.setPlayers(players);
    }
    // toss method is used to simulate toss before actual match starts, it will decide randomly which team won the toss
    // and what they have chosen first. In future functionality of asking the user what they wish to take first can be
    // added.
    public void toss() {

        Random r = new Random();
        // If "tossResult" is true, then team "a" won the toss and if result is false then team "b" won toss
        boolean tossResult = r.nextBoolean();

        if(tossResult) tossWinningTeam = teamA.getName();
        else tossWinningTeam = teamB.getName();

        // If "decision" is true, then winning team chooses bat first and if it is false then it chooses bowl first
        boolean decision = r.nextBoolean();

        if(decision) choseTo = "Bat";

        if(tossResult==decision) {
            firstInnings.setBattingTeam(teamA);
            firstInnings.setBowlingTeam(teamB);
            secondInnings.setBattingTeam(teamB);
            secondInnings.setBowlingTeam(teamA);
        } else {
            firstInnings.setBattingTeam(teamB);
            firstInnings.setBowlingTeam(teamA);
            secondInnings.setBattingTeam(teamA);
            secondInnings.setBowlingTeam(teamB);
        }
    }

    // startMatch method is the actual method that will start the match, it will call toss(), first.start(int) and
    // second.start(int)
    public Match startMatch(int overs) {

        setPlayers(teamA);
        setPlayers(teamB);
        numOfOvers = overs;

        toss();

        firstInnings.start(numOfOvers);
        secondInnings.start(numOfOvers);

        for(int i = 0;i < firstInnings.getBowlingTeam().getPlayers().length; i++) {

            if(firstInnings.getBowlingTeam().getPlayer(i).getBallsBowled()!=0)  firstInnings.getBowlingTeam()
                    .getPlayer(i).setEconomy(Math.round((double)firstInnings.getBowlingTeam().getPlayer(i).getRunsGiven()/
                    firstInnings.getBowlingTeam().getPlayer(i).getBallsBowled()*600.0)/100.0);

            if(firstInnings.getBowlingTeam().getPlayer(i).getBallsPlayed()!=0) firstInnings.getBowlingTeam().getPlayer(i)
                    .setStrike_rate(Math.round((double)firstInnings.getBowlingTeam().getPlayer(i).getRunsScored()/
                            firstInnings.getBowlingTeam().getPlayer(i).getBallsPlayed()*10000.0)/100.0);
        }

        for(int i = 0;i < secondInnings.getBowlingTeam().getPlayers().length; i++) {

            if(secondInnings.getBowlingTeam().getPlayer(i).getBallsBowled()!=0)  secondInnings.getBowlingTeam().getPlayer(i)
                    .setEconomy(Math.round((double)secondInnings.getBowlingTeam().getPlayer(i).getRunsGiven()/
                            secondInnings.getBowlingTeam().getPlayer(i).getBallsBowled()*600.0)/100.0);

            if(secondInnings.getBowlingTeam().getPlayer(i).getBallsPlayed()!=0) secondInnings.getBowlingTeam().getPlayer(i)
                    .setStrike_rate(Math.round((double)secondInnings.getBowlingTeam().getPlayer(i).getRunsScored()/
                            secondInnings.getBowlingTeam().getPlayer(i).getBallsPlayed()*10000.0)/100.0);

        }

        if(secondInnings.getBattingTeam().getRuns() > secondInnings.getBowlingTeam().getRuns()) {
            result = "Team " + secondInnings.getBattingTeam().getName() + " won by " +
                    (10 - secondInnings.getBattingTeam().getWickets());
            if(secondInnings.getBattingTeam().getWickets() == 9) result += " wicket!";
            else result += " wickets!";
        }
        else if(secondInnings.getBattingTeam().getRuns() < secondInnings.getBowlingTeam().getRuns()) {
            result = "Team "
                    + secondInnings.getBowlingTeam().getName() + " won by "
                    + (secondInnings.getBowlingTeam().getRuns() - secondInnings.getBattingTeam().getRuns());
            if(secondInnings.getBowlingTeam().getRuns()-secondInnings.getBattingTeam().getRuns()==1) result += " run!";
            else result += " runs!";
        }
        else result = "Match is tied!";

        return this;
    }
}
