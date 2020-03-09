package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketTeamRepository;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.Innings;
import com.example.CricketGameTrial.models.PlayerBattingScoreCard;
import com.example.CricketGameTrial.models.PlayerBowlingScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CricketPlayerService {

    @Autowired
    CricketTeamRepository ct_dao;

    public Collection<CricketPlayer> getAllPlayersFromTeam(String teamName) {
        return ct_dao.findById(teamName).get().getPlayers().values();
    }

    public CricketPlayer getPlayerFromTeam(String teamName, int jerseyNumber) {
        return ct_dao.findById(teamName).get().getPlayers().get(jerseyNumber);
    }

    public PlayerBattingScoreCard getPlayerWholeBattingStatsFromTeam(String teamName, int jerseyNumber) {
        return new PlayerBattingScoreCard(ct_dao.findById(teamName).get().getPlayers().get(jerseyNumber));
    }

    public PlayerBattingScoreCard getPlayerBattingStatsFromTeam(String teamName, int jerseyNumber, int matchID) {
        return new PlayerBattingScoreCard(ct_dao.findById(teamName).get().getPlayers().get(jerseyNumber),matchID);
    }

    public PlayerBowlingScoreCard getPlayerWholeBowlingStatsFromTeam(String teamName, int jerseyNumber) {
        return new PlayerBowlingScoreCard(ct_dao.findById(teamName).get().getPlayers().get(jerseyNumber));
    }

    public PlayerBowlingScoreCard getPlayerBowlingStatsFromTeam(String teamName, int jerseyNumber, int matchID) {
        return new PlayerBowlingScoreCard(ct_dao.findById(teamName).get().getPlayers().get(jerseyNumber),matchID);
    }

    public void updateRunsToStats(CricketPlayer battingPlayer, CricketPlayer bowlingPlayer, int matchID, int ran) {
        battingPlayer.getPlayerStats().get(matchID).addRunsScored(ran);
        battingPlayer.addWholeRunsScored(ran);
        bowlingPlayer.getPlayerStats().get(matchID).addRunsGiven(ran);
        bowlingPlayer.addWholeRunsGiven(ran);
    }

    public void updateBallsToStats(CricketPlayer battingPlayer, CricketPlayer bowlingPlayer, int matchID) {
        battingPlayer.getPlayerStats().get(matchID).addBallsPlayed();
        battingPlayer.addWholeBallsPlayed();
        bowlingPlayer.getPlayerStats().get(matchID).addBallsBowled();
        bowlingPlayer.addWholeBallsBowled();
    }

    public void doWhenRunIsSeven(CricketPlayer bowlingPlayer, int matchID) {
        bowlingPlayer.getPlayerStats().get(matchID).addWicketsTaken();
        bowlingPlayer.addWholeWicketsTaken();
    }

    void doWhenRunIsFour(CricketPlayer battingPlayer, int matchID) {
        battingPlayer.getPlayerStats().get(matchID).addNumOfFours();
        battingPlayer.addWholeNumOfFours();
    }

    void doWhenRunIsSix(CricketPlayer battingPlayer, int matchID) {
        battingPlayer.getPlayerStats().get(matchID).addNumOfSixes();
        battingPlayer.addWholeNumOfSixes();
    }
}
