package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.models.Innings;
import com.example.CricketGameTrial.models.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class InningsService {

    @Autowired
    OverService overs;

    @Autowired
    CricketTeamService cricketTeamService;

    @Autowired
    CricketPlayerService cricketPlayerService;

    // start method takes number of overs to be played as an argument because innings can only start by mentioning
    // number of overs to be played in it and returns number of overs played by the batting team
    public void startInnings(Innings innings, int numOfOvers, int matchID) {
        // balls represents number of balls to be played
        int count = 0; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        int battingPlayer = cricketTeamService.getTeam(innings.getBattingTeam()).getPlayers().get(0);
        int nonStrikerBattingPlayer = cricketTeamService.getTeam(innings.getBattingTeam()).getPlayers().get(1);
        int bowlingPlayer = cricketTeamService.getTeam(innings.getBowlingTeam()).getPlayers().get(index);

        while(count<numOfOvers && innings.getWickets()<10) {
            innings.getOvers().add(new Over(battingPlayer, nonStrikerBattingPlayer, bowlingPlayer));

            int runsInOver = overs.startOver(innings,matchID);

            if(innings.getOvers().get(innings.getOvers().size()-1).getBalls().size()==6 && runsInOver==0) {
                cricketPlayerService.getPlayer(bowlingPlayer).getPlayerStats().get(matchID).addNumOfMaidenOvers();
                cricketPlayerService.getPlayer(bowlingPlayer).addWholeNumOfMaidenOvers();
            }

            if(index==10) index = 6;
            else index++;
            bowlingPlayer = cricketTeamService.getTeam(innings.getBowlingTeam()).getPlayers().get(index);

            int temp = battingPlayer;
            battingPlayer = nonStrikerBattingPlayer;
            nonStrikerBattingPlayer = temp;
            count++;
        }
    }

    public void startInnings(Innings innings, int numOfOvers, int target, int matchID) {
        // balls represents number of balls to be played
        int count = 0; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        int battingPlayer = cricketTeamService.getTeam(innings.getBattingTeam()).getPlayers().get(0);
        int nonStrikerBattingPlayer = cricketTeamService.getTeam(innings.getBattingTeam()).getPlayers().get(1);
        int bowlingPlayer = cricketTeamService.getTeam(innings.getBowlingTeam()).getPlayers().get(index);

        while(count<numOfOvers && innings.getWickets()<10 && innings.getRuns()<=target) {
            innings.getOvers().add(new Over(battingPlayer, nonStrikerBattingPlayer, bowlingPlayer));
            int runsInOver = overs.startOver(innings, target, matchID);

            if(innings.getOvers().get(innings.getOvers().size()-1).getBalls().size()==6 && runsInOver==0) {
                cricketPlayerService.getPlayer(bowlingPlayer).getPlayerStats().get(matchID).addNumOfMaidenOvers();
                cricketPlayerService.getPlayer(bowlingPlayer).addWholeNumOfMaidenOvers();
            }

            if(index==10) index = 6;
            else index++;
            bowlingPlayer = cricketTeamService.getTeam(innings.getBowlingTeam()).getPlayers().get(index);

            int temp = battingPlayer;
            battingPlayer = nonStrikerBattingPlayer;
            nonStrikerBattingPlayer = temp;
            count++;
        }
    }
}
