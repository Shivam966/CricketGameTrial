package com.example.CricketGameTrial.domain;

import com.example.CricketGameTrial.util.Scoreboard;

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
    private Team tossWinningTeam;

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

        if(tossResult) tossWinningTeam = a;
        else tossWinningTeam = b;

        // If "decision" is true, then winning team chooses bat first and if it is false then it chooses bowl first
        boolean decision = r.nextBoolean();

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
    public String startMatch(int overs) {

        setPlayers(a);
        setPlayers(b);
        numOfOvers = overs;
        // Using System.lineSeparator() for newline so that code will be platform independent.
        String newline = System.lineSeparator();
        // res contains the final result string that startMatch() will return
        // StringBuilder is used to improve performance instead of string because string is immutable every time we
        // concatenate two strings new string is created
        StringBuilder res = new StringBuilder();
        toss();
        res.append("<b> Team ").append(first.getBattingTeam().getName()).append(" vs Team ")
                .append(first.getBowlingTeam().getName()).append(" </b>").append("<br>").append("<br>")
                .append(newline);
        if(tossWinningTeam == first.getBattingTeam()) {
            res.append("Team ").append(tossWinningTeam.getName()).append(" won the toss and chose to bat first.")
                    .append("<br>").append(newline);
        } else {
            res.append("Team ").append(tossWinningTeam.getName()).append(" won the toss and chose to bowl first.")
                    .append("<br>").append(newline);
        }

        first.start(numOfOvers);
        second.start(numOfOvers);
        res.append(Scoreboard.printScoreBoard(first,second));

        first.getOvers().clear();
        second.getOvers().clear();
        return res.toString();
    }
}
