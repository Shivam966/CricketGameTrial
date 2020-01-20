package com.example.CricketGameTrial.domain;

import java.util.Random;

public class Match {
    /*
    Match class will have 2 team objects because each match comprises of 2 teams but vice versa is not true, thus it is
    an Aggregation relationship.
    In the same way it will have 2 innings-first and second, 1 numOfOvers variable to determine how many overs each
    inning will play and 1 tossWinningTeam variable that will store the toss winning team.
    */
    private Team a = new Team("India"), b = new Team("Pakistan");
    private FirstInnings first = new FirstInnings();
    private SecondInnings second = new SecondInnings();
    private int numOfOvers = 50;
    private Team tossWinningTeam;

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

        if(!(tossResult^decision)) {
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
    public String startMatch() {

        // Using System.lineSeparator() for newline so that code will be platform independent.
        String newline = System.lineSeparator();
        // res contains the final result string that startMatch() will return
        // StringBuilder is used to improve performance instead of string because string is immutable every time we
        // concatenate two strings new string is created
        StringBuilder res = new StringBuilder();
        toss();
        res.append("<b> Team ").append(first.getBattingTeam().getName()).append(" vs Team ")
                .append(first.getBowlingTeam().getName()).append(" </b>").append("<br>").append(newline);
        if(tossWinningTeam == first.getBattingTeam()) {
            res.append("Team ").append(tossWinningTeam.getName()).append(" won the toss and chose to bat first.")
                    .append("<br>").append(newline);
        } else {
            res.append("Team ").append(tossWinningTeam.getName()).append(" won the toss and chose to bowl first.")
                    .append("<br>").append(newline);
        }

        int numOfBalls = first.start(numOfOvers);
        String overs = new String();
        overs += (numOfBalls/6);
        if(numOfBalls%6 != 0) overs += "."+(numOfBalls%6);

        res.append("First Innings Score").append("<br>").append(newline);
        res.append("Team ").append(first.getBattingTeam().getName()).append(" : ").append(first.getBattingTeam()
                .getRuns()).append("/").append(first.getBattingTeam().getWickets()).append(" (").append(overs)
                .append(")").append("<br>").append(newline);

        numOfBalls = second.start(numOfOvers);
        overs = new String();
        overs += (numOfBalls/6);
        if(numOfBalls%6 != 0) overs += "."+(numOfBalls%6);

        res.append("Second Innings Score").append("<br>").append(newline);
        res.append("Team ").append(second.getBattingTeam().getName()).append(" : ").append(second.getBattingTeam()
                .getRuns()).append("/").append(second.getBattingTeam().getWickets()).append(" (").append(overs)
                .append(")").append("<br>").append(newline);

        // After second innings if batting team runs are greater than bowling team runs then batting team wins
        if(second.getBattingTeam().getRuns() > second.getBowlingTeam().getRuns()) {
            res.append("Team ").append(second.getBattingTeam().getName()).append(" wins against Team ")
                    .append(second.getBowlingTeam().getName()).append(" by ").append((10-second.getBattingTeam()
                    .getWickets())).append(" wickets!").append("<br>").append(newline);
        } else if(second.getBattingTeam().getRuns() < second.getBowlingTeam().getRuns()) {
            res.append("Team ").append(second.getBowlingTeam().getName()).append(" wins against Team ")
                    .append(second.getBattingTeam().getName()).append(" by ").append((second.getBowlingTeam().getRuns()
                    - second.getBattingTeam().getRuns())).append(" runs!").append("<br>").append(newline);
        } else {
            res.append("Match between Team ").append(second.getBattingTeam().getName()).append(" and Team ")
                    .append(second.getBowlingTeam().getName()).append(" is tied!").append("<br>").append(newline);
        }
        return res.toString();
    }
}
