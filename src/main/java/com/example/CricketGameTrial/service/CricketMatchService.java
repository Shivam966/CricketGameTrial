package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchDAO;
import com.example.CricketGameTrial.DAO.CricketMatchDAOImpl;
import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketTeam;

import java.util.List;
import java.util.Random;

public abstract class CricketMatchService {
    private static CricketMatchDAO cm_dao = new CricketMatchDAOImpl();

    public static List<CricketMatch> getAllMatches() {
        return cm_dao.getAllMatches();
    }

    public static CricketMatch startMatch(int numOfOvers, String team1, String team2) {
        cm_dao.createMatch(numOfOvers,team1,team2);
        CricketMatch currentMatch = cm_dao.getMatch(cm_dao.getAllMatches().size()-1);
        doToss(currentMatch);
        InningsService.start(currentMatch.getFirstInnings(),numOfOvers);
        InningsService.start(currentMatch.getSecondInnings(),numOfOvers,currentMatch.getFirstInnings().getRuns());

        setEconomyAndStrikeRate(currentMatch.getTeamA());
        setEconomyAndStrikeRate(currentMatch.getTeamB());

        String result;
        if(currentMatch.getSecondInnings().getRuns() > currentMatch.getFirstInnings().getRuns()) {
            result = "Team " + currentMatch.getSecondInnings().getBattingTeam().getName() + " won by " +
                    (10 - currentMatch.getSecondInnings().getWickets());
            if(currentMatch.getSecondInnings().getWickets() == 9) result += " wicket!";
            else result += " wickets!";
        }
        else if(currentMatch.getSecondInnings().getRuns() < currentMatch.getFirstInnings().getRuns()) {
            result = "Team "
                    + currentMatch.getSecondInnings().getBowlingTeam().getName() + " won by "
                    + (currentMatch.getFirstInnings().getRuns() - currentMatch.getSecondInnings().getRuns());
            if(currentMatch.getSecondInnings().getRuns()-currentMatch.getFirstInnings().getRuns()==1) result += " run!";
            else result += " runs!";
        }
        else result = "Match is tied!";
        currentMatch.setResult(result);
        return currentMatch;
    }

    static void setEconomyAndStrikeRate(CricketTeam team) {
        for(int i = 0;i < team.getPlayers().size(); i++) {

            if(team.getPlayers().get(i).getBallsBowled()!=0)  team
                    .getPlayers().get(i).setEconomy(Math.round((float)team.getPlayers().get(i).getRunsGiven()/
                            team.getPlayers().get(i).getBallsBowled()*600.0f)/100.0f);

            if(team.getPlayers().get(i).getBallsPlayed()!=0) team.getPlayers().get(i)
                    .setStrike_rate(Math.round((float)team.getPlayers().get(i).getRunsScored()/
                            team.getPlayers().get(i).getBallsPlayed()*10000.0f)/100.0f);
        }
    }

    // toss method is used to simulate toss before actual match starts, it will decide randomly which team won the toss
    // and what they have chosen first. In future functionality of asking the user what they wish to take first can be
    // added.
    static void doToss(CricketMatch match) {
        Random r = new Random();
        // If "tossResult" is true, then team "a" won the toss and if result is false then team "b" won toss
        boolean tossResult = r.nextBoolean();

        if(tossResult) match.setTossWinningTeam(match.getTeamA().getName());
        else match.setTossWinningTeam(match.getTeamB().getName());

        // If "decision" is true, then winning team chooses bat first and if it is false then it chooses bowl first
        boolean decision = r.nextBoolean();

        if(decision) match.setChoseTo("Bat");
        else match.setChoseTo("Bowl");

        if(tossResult==decision) {
            match.getFirstInnings().setBattingTeam(match.getTeamA());
            match.getFirstInnings().setBowlingTeam(match.getTeamB());
            match.getSecondInnings().setBattingTeam(match.getTeamB());
            match.getSecondInnings().setBowlingTeam(match.getTeamA());
        } else {
            match.getFirstInnings().setBattingTeam(match.getTeamB());
            match.getFirstInnings().setBowlingTeam(match.getTeamA());
            match.getSecondInnings().setBattingTeam(match.getTeamA());
            match.getSecondInnings().setBowlingTeam(match.getTeamB());
        }
    }
}
