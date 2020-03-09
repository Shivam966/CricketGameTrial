package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.models.Innings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
class OverService {

    @Autowired
    CricketPlayerService cricketPlayerService;

    @Autowired
    CricketTeamService cricketTeamService;

    public int startOver(Innings innings, int matchID, int target) {
        int ran;
        int runsInOver = 0;
        int c = 0;
        CricketTeam battingTeam = cricketTeamService.getTeam(innings.getBattingTeam());
        CricketTeam bowlingTeam = cricketTeamService.getTeam(innings.getBowlingTeam());
        while(c < 6 && innings.getWickets()<10) {
            if(target>=0 && innings.getRuns() > target) break;
            ran = generateRunsByRating(cricketPlayerService.getPlayerFromTeam(innings.getBattingTeam(),
                    innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).getPlayerRating());

            CricketPlayer battingPlayer = cricketPlayerService.getPlayerFromTeam(innings.getBattingTeam(),
                    innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer());
            CricketPlayer bowlingPlayer = cricketPlayerService.getPlayerFromTeam(innings.getBowlingTeam(),
                    innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer());


            cricketPlayerService.updateBallsToStats(battingPlayer, bowlingPlayer, matchID);
            if (ran == 7) {
                innings.setWickets(innings.getWickets()+1);
                innings.getOvers().get(innings.getOvers().size()-1).setBattingPlayer((new ArrayList<>(battingTeam
                        .getPlayers().values())).get(innings.getWickets()).getJerseyNumber());
                cricketPlayerService.doWhenRunIsSeven(bowlingPlayer, matchID);
            } else {
                innings.setRuns(innings.getRuns()+ran);
                runsInOver += ran;

                cricketPlayerService.updateRunsToStats(battingPlayer, bowlingPlayer, matchID, ran);

                if(ran==4) cricketPlayerService.doWhenRunIsFour(battingPlayer,matchID);
                if(ran==6) cricketPlayerService.doWhenRunIsSix(battingPlayer,matchID);
            }
            if(ran<7) innings.getOvers().get(innings.getOvers().size()-1).getBalls().add(Character.forDigit(ran,
                    10));
            else innings.getOvers().get(innings.getOvers().size()-1).getBalls().add('W');
            if(ran%2!=0) doWhenRunIsOdd(innings);
            battingTeam.getPlayers().put(battingPlayer.getJerseyNumber(), battingPlayer);
            bowlingTeam.getPlayers().put(bowlingPlayer.getJerseyNumber(), bowlingPlayer);
            c++;
        }
        cricketTeamService.addTeam(battingTeam);
        cricketTeamService.addTeam(bowlingTeam);
        return runsInOver;
    }

    int generateRunsByRating(int rating) {
        int ran;
        float probability = (float)Math.random();
        if(probability > ((float)rating/100.0f)) ran = 7;
        else {
            ran = (int)(Math.random()*7);
        }
        return ran;
    }

    void doWhenRunIsOdd(Innings innings) {
        int battingPlayer = innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer();
        int nonStrikerBattingPlayer = innings.getOvers().get(innings.getOvers().size()-1).getNonStrikerBattingPlayer();
        innings.getOvers().get(innings.getOvers().size()-1).setBattingPlayer(nonStrikerBattingPlayer);
        innings.getOvers().get(innings.getOvers().size()-1).setNonStrikerBattingPlayer(battingPlayer);
    }
}
