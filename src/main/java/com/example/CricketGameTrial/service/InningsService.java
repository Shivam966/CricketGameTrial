package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.Innings;
import com.example.CricketGameTrial.models.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class InningsService {

    @Autowired
    OverService overs;

    @Autowired
    CricketTeamService cricketTeamService;

    @Autowired
    CricketPlayerService cricketPlayerService;

    public void startInnings(CricketMatch match, boolean isFirstInnings) {

        Innings innings;
        if(isFirstInnings) innings = match.getFirstInnings();
        else innings = match.getSecondInnings();

        int count = 0;
        int index = 6;

        List<CricketPlayer> battingTeam = new ArrayList<>(cricketTeamService.getTeam(innings.getBattingTeam())
                .getPlayers().values());
        List<CricketPlayer> bowlingTeam = new ArrayList<>(cricketTeamService.getTeam(innings.getBowlingTeam())
                .getPlayers().values());

        int battingPlayer = battingTeam.get(0).getJerseyNumber();
        int nonStrikerBattingPlayer = battingTeam.get(1).getJerseyNumber();
        int bowlingPlayer = bowlingTeam.get(index).getJerseyNumber();

        while(count<match.getNumOfOvers() && innings.getWickets()<10) {
            if(!isFirstInnings && innings.getRuns() > match.getFirstInnings().getRuns()) break;
            innings.getOvers().add(new Over(battingPlayer, nonStrikerBattingPlayer,
                    bowlingPlayer));

            int runsInOver;
            if(isFirstInnings)
              runsInOver = overs.startOver(innings,match.getMatchID(), -1);
            else runsInOver = overs.startOver(innings, match.getMatchID(), match.getFirstInnings().getRuns());

            if(innings.getOvers().get(innings.getOvers().size()-1).getBalls().size()==6 && runsInOver==0) {
                cricketPlayerService.getPlayerFromTeam(innings.getBowlingTeam(), bowlingPlayer).getPlayerStats()
                        .get(match.getMatchID()).addNumOfMaidenOvers();
                cricketPlayerService.getPlayerFromTeam(innings.getBowlingTeam(), bowlingPlayer).addWholeNumOfMaidenOvers();
            }

            if(index==10) index = 6;
            else index++;
            bowlingPlayer = (new ArrayList<>(cricketTeamService.getTeam(innings.getBowlingTeam()).getPlayers()
                    .values())).get(index).getJerseyNumber();

            int temp = battingPlayer;
            battingPlayer = nonStrikerBattingPlayer;
            nonStrikerBattingPlayer = temp;
            count++;
        }
    }
}
