package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketPlayer;

import java.util.HashMap;
import java.util.Map;

public class CricketPlayerDAOImpl implements CricketPlayerDAO {

    private Map<Integer,CricketPlayer> players;

    public CricketPlayerDAOImpl() {
        players = new HashMap<>();
    }

    @Override
    public Map<Integer, CricketPlayer> getAllPlayers() {
        return players;
    }

    @Override
    public CricketPlayer getPlayer(int jerseyNumber) {   // index and jersey number will be equal for each player
        return players.get(jerseyNumber);
    }

    @Override
    public void savePlayer(CricketPlayer player) {
        players.put(player.getJerseyNumber(), player);
    }

    @Override
    public void deletePlayer(CricketPlayer player) {
        players.remove(player.getJerseyNumber());
    }
}
