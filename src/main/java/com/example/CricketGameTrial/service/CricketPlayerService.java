package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchRepository;
import com.example.CricketGameTrial.DAO.CricketPlayerRepository;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.Innings;
import com.example.CricketGameTrial.models.PlayerBattingScoreCard;
import com.example.CricketGameTrial.models.PlayerBowlingScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CricketPlayerService {

    @Autowired
    CricketPlayerRepository cp_dao;

    @Autowired
    CricketTeamService cricketTeamService;

    public List<CricketPlayer> getAllPlayers() {
        return cp_dao.findAll();
    }

    public CricketPlayer getPlayer(int jerseyNumber) {
        return cp_dao.findById(jerseyNumber).get();
    }

    public PlayerBattingScoreCard getPlayerWholeBattingStats(int jerseyNumber) {
        return new PlayerBattingScoreCard(cp_dao.findById(jerseyNumber).get());
    }

    public PlayerBattingScoreCard getPlayerBattingStats(int jerseyNumber, int matchID) {
        return new PlayerBattingScoreCard(cp_dao.findById(jerseyNumber).get(),matchID);
    }

    public PlayerBowlingScoreCard getPlayerWholeBowlingStats(int jerseyNumber) {
        return new PlayerBowlingScoreCard(cp_dao.findById(jerseyNumber).get());
    }

    public PlayerBowlingScoreCard getPlayerBowlingStats(int jerseyNumber, int matchID) {
        return new PlayerBowlingScoreCard(cp_dao.findById(jerseyNumber).get(),matchID);
    }

    public void updateRunsToStats(Innings innings, int matchID, int ran) {
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).get()
                .getPlayerStats().get(matchID).addRunsScored(ran);
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).get()
                .addWholeRunsScored(ran);
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer()).get()
                .getPlayerStats().get(matchID).addRunsGiven(ran);
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer()).get()
                .addWholeRunsGiven(ran);
    }

    public void updateBallsToStats(Innings innings, int matchID) {
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).get()
                .getPlayerStats().get(matchID).addBallsPlayed();
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).get()
                .addWholeBallsPlayed();
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer()).get()
                .getPlayerStats().get(matchID).addBallsBowled();
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer()).get()
                .addWholeBallsBowled();
    }

    public void doWhenRunIsSeven(Innings innings, int matchID) {
        innings.setWickets(innings.getWickets()+1);
        innings.getOvers().get(innings.getOvers().size()-1).setBattingPlayer(cricketTeamService.getTeam(innings
                .getBattingTeam()).getPlayers().get(innings.getWickets()));
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer()).get()
                .getPlayerStats().get(matchID).addWicketsTaken();
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBowlingPlayer()).get()
                .addWholeWicketsTaken();
    }

    void doWhenRunIsFour(Innings innings, int matchID) {
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1)
                .getBattingPlayer()).get().getPlayerStats().get(matchID).addNumOfFours();
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).get()
                .addWholeNumOfFours();
    }

    void doWhenRunIsSix(Innings innings, int matchID) {
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1)
                .getBattingPlayer()).get().getPlayerStats().get(matchID).addNumOfSixes();
        cp_dao.findById(innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer()).get()
                .addWholeNumOfSixes();
    }
}
