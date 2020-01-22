package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.util.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Match {
    /*
    Match class will have 2 team objects because each match comprises of 2 teams but vice versa is not true, thus it is
    an Aggregation relationship.
    In the same way it will have 2 innings-first and second, 1 numOfOvers variable to determine how many overs each
    inning will play and 1 tossWinningTeam variable that will store the toss winning team.
    */
    private Team a,b;
    private FirstInnings first = new FirstInnings();
    private SecondInnings second = new SecondInnings();
    private int numOfOvers;
    private String tossWinningTeam;
    private String choseTo = "Bowl";

    public void setTeamName(String team1, String team2) {
        a = new Team(team1);
        b = new Team(team2);
    }

    public Team getTeamA() {
        return a;
    }

    public Team getTeamB() {
        return b;
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

        if(tossResult) tossWinningTeam = a.getName();
        else tossWinningTeam = b.getName();

        // If "decision" is true, then winning team chooses bat first and if it is false then it chooses bowl first
        boolean decision = r.nextBoolean();

        if(decision) choseTo = "Bat";

        if(tossResult==decision) {
            first.setBattingTeam(a);
            first.setBowlingTeam(b);
            second.setBattingTeam(b);
            second.setBowlingTeam(a);
        } else {
            first.setBattingTeam(b);
            first.setBowlingTeam(a);
            second.setBattingTeam(a);
            second.setBowlingTeam(b);
        }
    }

    // startMatch method is the actual method that will start the match, it will call toss(), first.start(int) and
    // second.start(int)
    public Scoreboard startMatch(int overs) {

        setPlayers(a);
        setPlayers(b);
        numOfOvers = overs;

        toss();

        first.start(numOfOvers);
        second.start(numOfOvers);

        for(int i = 0;i < first.getBowlingTeam().getPlayers().length; i++) {

            if(first.getBowlingTeam().getPlayer(i).getBallsBowled()!=0)  first.getBowlingTeam().getPlayer(i)
                    .setEconomy(Math.round((double)first.getBowlingTeam().getPlayer(i).getRunsGiven()/
                    first.getBowlingTeam().getPlayer(i).getBallsBowled()*600.0)/100.0);

            if(first.getBowlingTeam().getPlayer(i).getBallsPlayed()!=0) first.getBowlingTeam().getPlayer(i)
                    .setStrike_rate(Math.round((double)first.getBowlingTeam().getPlayer(i).getRunsScored()/
                            first.getBowlingTeam().getPlayer(i).getBallsPlayed()*10000.0)/100.0);
        }

        for(int i = 0;i < second.getBowlingTeam().getPlayers().length; i++) {

            if(second.getBowlingTeam().getPlayer(i).getBallsBowled()!=0)  second.getBowlingTeam().getPlayer(i)
                    .setEconomy(Math.round((double)second.getBowlingTeam().getPlayer(i).getRunsGiven()/
                            second.getBowlingTeam().getPlayer(i).getBallsBowled()*600.0)/100.0);

            if(second.getBowlingTeam().getPlayer(i).getBallsPlayed()!=0) second.getBowlingTeam().getPlayer(i)
                    .setStrike_rate(Math.round((double)second.getBowlingTeam().getPlayer(i).getRunsScored()/
                            second.getBowlingTeam().getPlayer(i).getBallsPlayed()*10000.0)/100.0);

        }

        String result;
        if(second.getBattingTeam().getRuns() > second.getBowlingTeam().getRuns()) {
            result = "Team "
                    + second.getBattingTeam().getName() + " won by " + (10 - second.getBattingTeam().getWickets());
            if(second.getBattingTeam().getWickets() == 9) result += " wicket!";
            else result += " wickets!";
        }
        else if(second.getBattingTeam().getRuns() < second.getBowlingTeam().getRuns()) {
            result = "Team "
                    + second.getBowlingTeam().getName() + " won by "
                    + (second.getBowlingTeam().getRuns() - second.getBattingTeam().getRuns());
            if(second.getBowlingTeam().getRuns()-second.getBattingTeam().getRuns()==1) result += " run!";
            else result += " runs!";
        }
        else result = "Match is tied!";

        ArrayList<Over> firstInningsOver = new ArrayList<>(first.getOvers()),
                secondInningsOver = new ArrayList<>(second.getOvers());

        first.getOvers().clear();
        second.getOvers().clear();

        return new Scoreboard(overs, tossWinningTeam, choseTo, a, b, firstInningsOver, secondInningsOver, result);
    }
}
