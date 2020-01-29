package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchDAO;
import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class CricketMatchService {

    @Autowired
    @Qualifier("com.example.CricketGameTrial.DAO.CricketMatchDAOImpl")
    CricketMatchDAO cm_dao;

    @Autowired
    InningsService innings;

    @Autowired
    CricketTeamService cricketTeamService;

    @Autowired
    CricketPlayerService cricketPlayerService;

    public Map<Integer, CricketMatch> getAllMatches() {
        return cm_dao.getAllMatches();
    }

    public CricketMatch startMatch(CricketMatch match) {
        cm_dao.createMatch(match);
        doToss(match);
        addMatchIDToPlayers(match.getTeamA(),match.getTeamB(), match.getMatchID());
        innings.startInnings(match.getFirstInnings(),match.getNumOfOvers(),match.getMatchID());
        innings.startInnings(match.getSecondInnings(),match.getNumOfOvers(),match.getFirstInnings().getRuns()
                ,match.getMatchID());

        setEconomyAndStrikeRate(match.getTeamA(),match.getMatchID());
        setEconomyAndStrikeRate(match.getTeamB(),match.getMatchID());

        String result;
        if(match.getSecondInnings().getRuns() > match.getFirstInnings().getRuns()) {
            result = "Team " + match.getSecondInnings().getBattingTeam() + " won by " +
                    (10 - match.getSecondInnings().getWickets());
            if(match.getSecondInnings().getWickets() == 9) result += " wicket!";
            else result += " wickets!";
        }
        else if(match.getSecondInnings().getRuns() < match.getFirstInnings().getRuns()) {
            result = "Team "
                    + match.getSecondInnings().getBowlingTeam() + " won by "
                    + (match.getFirstInnings().getRuns() - match.getSecondInnings().getRuns());
            if(match.getSecondInnings().getRuns()-match.getFirstInnings().getRuns()==1) result += " run!";
            else result += " runs!";
        }
        else result = "Match is tied!";
        match.setResult(result);
        return match;
    }

    void addMatchIDToPlayers(String team1, String team2, int matchID) {
        for(int i=0;i< cricketTeamService.getTeam(team1).getPlayers().size();i++) {
            cricketPlayerService.getPlayer(cricketTeamService.getTeam(team1).getPlayers().get(i)).getPlayerStats()
                    .put(matchID, new Stats());
        }
        for(int i=0;i < cricketTeamService.getTeam(team2).getPlayers().size();i++) {
            cricketPlayerService.getPlayer(cricketTeamService.getTeam(team2).getPlayers().get(i)).getPlayerStats()
                    .put(matchID, new Stats());
        }
    }

    void setEconomyAndStrikeRate(String team, int matchID) {
        for(int i = 0;i < cricketTeamService.getTeam(team).getPlayers().size(); i++) {

            if(cricketPlayerService.getPlayer(cricketTeamService.getTeam(team).getPlayers().get(i)).getPlayerStats()
                    .get(matchID).getBallsBowled()!=0) cricketPlayerService.getPlayer(cricketTeamService.getTeam(team)
                    .getPlayers().get(i)).getPlayerStats().get(matchID).setPlayerEconomy(Math.round((float)
                    cricketPlayerService.getPlayer(cricketTeamService.getTeam(team).getPlayers().get(i))
                            .getPlayerStats().get(matchID).getRunsGiven()/ cricketPlayerService
                    .getPlayer(cricketTeamService.getTeam(team).getPlayers().get(i)).getPlayerStats().get(matchID)
                    .getBallsBowled()*600.0f)/100.0f);

            if(cricketPlayerService.getPlayer(cricketTeamService.getTeam(team).getPlayers().get(i)).getPlayerStats()
                    .get(matchID).getBallsPlayed()!=0) cricketPlayerService.getPlayer(cricketTeamService.getTeam(team)
                    .getPlayers().get(i)).getPlayerStats().get(matchID).setPlayerStrike_rate(Math.round((float)
                    cricketPlayerService.getPlayer(cricketTeamService.getTeam(team).getPlayers().get(i))
                            .getPlayerStats().get(matchID).getRunsScored()/ cricketPlayerService
                    .getPlayer(cricketTeamService.getTeam(team).getPlayers().get(i)).getPlayerStats().get(matchID)
                    .getBallsPlayed()*10000.0f)/100.0f);
        }
    }

    // toss method is used to simulate toss before actual match starts, it will decide randomly which team won the toss
    // and what they have chosen first. In future functionality of asking the user what they wish to take first can be
    // added.
    void doToss(CricketMatch match) {
        Random r = new Random();
        // If "tossResult" is true, then team "a" won the toss and if result is false then team "b" won toss
        boolean tossResult = r.nextBoolean();

        if(tossResult) match.setTossWinningTeam(match.getTeamA());
        else match.setTossWinningTeam(match.getTeamB());

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
