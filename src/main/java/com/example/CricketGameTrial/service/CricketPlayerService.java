package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchDAO;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.Innings;
import com.example.CricketGameTrial.models.PlayerBattingScoreCard;
import com.example.CricketGameTrial.models.PlayerBowlingScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CricketPlayerService {

    @Autowired
    CricketMatchDAO cm_dao;

    @Autowired
    CricketTeamService cricketTeamService;

    public Map<Integer, CricketPlayer> getAllPlayers() {
        return cm_dao.getAllPlayers();
    }

    public CricketPlayer getPlayer(int jerseyNumber) {
        return cm_dao.getPlayer(jerseyNumber);
    }

    public PlayerBattingScoreCard getPlayerWholeBattingStats(int jerseyNumber) {
        return new PlayerBattingScoreCard(cm_dao.getPlayer(jerseyNumber));
    }

    public PlayerBattingScoreCard getPlayerBattingStats(int jerseyNumber, int matchID) {
        return new PlayerBattingScoreCard(cm_dao.getPlayer(jerseyNumber),matchID);
    }

    public PlayerBowlingScoreCard getPlayerWholeBowlingStats(int jerseyNumber) {
        return new PlayerBowlingScoreCard(cm_dao.getPlayer(jerseyNumber));
    }

    public PlayerBowlingScoreCard getPlayerBowlingStats(int jerseyNumber, int matchID) {
        return new PlayerBowlingScoreCard(cm_dao.getPlayer(jerseyNumber),matchID);
    }

    public void updateRunsToStats(Innings innings, int matchID, int ran) {
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer())
                .getPlayerStats().get(matchID).addRunsScored(ran);
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer())
                .addWholeRunsScored(ran);
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer())
                .getPlayerStats().get(matchID).addRunsGiven(ran);
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer())
                .addWholeRunsGiven(ran);
    }

    public void updateBallsToStats(Innings innings, int matchID) {
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer())
                .getPlayerStats().get(matchID).addBallsPlayed();
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer())
                .addWholeBallsPlayed();
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer())
                .getPlayerStats().get(matchID).addBallsBowled();
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer())
                .addWholeBallsBowled();
    }

    public void doWhenRunIsSeven(Innings innings, int matchID) {
        innings.setWickets(innings.getWickets()+1);
        innings.getOvers().get(innings.getOvers().size()-1).setBattingPlayer(cricketTeamService.getTeam(innings
                .getBattingTeam()).getPlayers().get(innings.getWickets()));
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer())
                .getPlayerStats().get(matchID).addWicketsTaken();
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer())
                .addWholeWicketsTaken();
    }

    void doWhenRunIsFour(Innings innings, int matchID) {
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1)
                .getBattingPlayer()).getPlayerStats().get(matchID).addNumOfFours();
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer())
                .addWholeNumOfFours();
    }

    void doWhenRunIsSix(Innings innings, int matchID) {
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1)
                .getBattingPlayer()).getPlayerStats().get(matchID).addNumOfSixes();
        cm_dao.getPlayer(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer())
                .addWholeNumOfSixes();
    }
}
