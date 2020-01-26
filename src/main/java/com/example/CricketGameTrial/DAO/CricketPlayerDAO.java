package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketPlayer;

import java.util.List;
import java.util.Map;

public interface CricketPlayerDAO {

    Map<Integer, CricketPlayer> getAllPlayers();
    CricketPlayer getPlayer(int jerseyNumber);
    void savePlayer(CricketPlayer player);
    void deletePlayer(CricketPlayer player);
}
