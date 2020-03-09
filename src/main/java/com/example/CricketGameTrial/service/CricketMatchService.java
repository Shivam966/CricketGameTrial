package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchRepository;
import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.models.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CricketMatchService {

    @Autowired
    CricketMatchRepository cm_dao;

    @Autowired
    InningsService innings;

    @Autowired
    CricketTeamService cricketTeamService;

    @Autowired
    CricketPlayerService cricketPlayerService;

    public List<CricketMatch> getAllMatches() {
        return cm_dao.findAll();
    }

    public CricketMatch getMatch(int matchID) {return cm_dao.findById(matchID).get();}

    public CricketMatch startMatch(CricketMatch match) {
        doToss(match);
        addMatchIDToPlayers(match.getTeamA(), match.getMatchID());
        addMatchIDToPlayers(match.getTeamB(), match.getMatchID());
        innings.startInnings(match, true);
        innings.startInnings(match, false);

        setEconomyAndStrikeRate(match.getTeamA(),match.getMatchID());
        setEconomyAndStrikeRate(match.getTeamB(),match.getMatchID());
        setWholeEconomyAndStrikeRate(match.getTeamA());
        setWholeEconomyAndStrikeRate(match.getTeamB());

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
        cm_dao.save(match);
        return match;
    }

    void addMatchIDToPlayers(String team, int matchID) {
        CricketTeam tempTeam = cricketTeamService.getTeam(team);
        for(CricketPlayer player : tempTeam.getPlayers().values()) {
            player.getPlayerStats().put(matchID, new Stats());
            tempTeam.getPlayers().put(player.getJerseyNumber(), player);
        }
        cricketTeamService.addTeam(tempTeam);
    }

    void setWholeEconomyAndStrikeRate(String team) {
        CricketTeam tempTeam = cricketTeamService.getTeam(team);
        for(CricketPlayer player : tempTeam.getPlayers().values()) {

            if(player.getWholeBallsBowled()!=0) player.setPlayerWholeEconomy(Math.round((float)
                    player.getWholeRunsGiven()/ player.getWholeBallsBowled()*600.0f)/100.0f);

            if(player.getWholeBallsPlayed()!=0) player.setPlayerWholeStrike_rate(Math.round((float)
                    player.getWholeRunsScored()/ player.getWholeBallsPlayed()*10000.0f)/100.0f);
        }
        cricketTeamService.addTeam(tempTeam);
    }

    void setEconomyAndStrikeRate(String team,int matchID) {
        CricketTeam tempTeam = cricketTeamService.getTeam(team);
        for(CricketPlayer player : tempTeam.getPlayers().values()) {

            if(player.getPlayerStats().get(matchID).getBallsBowled()!=0) player.getPlayerStats().get(matchID)
                    .setPlayerEconomy(Math.round((float)
                    player.getPlayerStats().get(matchID).getRunsGiven()/ player.getPlayerStats().get(matchID)
                    .getBallsBowled()*600.0f)/100.0f);

            if(player.getPlayerStats().get(matchID).getBallsPlayed()!=0) player.getPlayerStats().get(matchID)
                    .setPlayerStrike_rate(Math.round((float)
                    player.getPlayerStats().get(matchID).getRunsScored()/player.getPlayerStats().get(matchID)
                    .getBallsPlayed()*10000.0f)/100.0f);
        }
        cricketTeamService.addTeam(tempTeam);
    }

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
