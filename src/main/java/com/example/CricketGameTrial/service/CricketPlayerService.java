package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketPlayerDAO;
import com.example.CricketGameTrial.DAO.CricketPlayerDAOImpl;
import com.example.CricketGameTrial.models.CricketPlayer;

import java.util.Map;

public abstract class CricketPlayerService {
    private static CricketPlayerDAO cp_dao = new CricketPlayerDAOImpl();

    public static void addPlayer(String name,String team ,int jerseyNumber, int age) {
        cp_dao.savePlayer(new CricketPlayer(name, team, jerseyNumber,age));
    }

    public static Map<Integer, CricketPlayer> getAllPlayers() {
        return cp_dao.getAllPlayers();
    }

    public static void removePlayer(int jerseyNumber) {
        cp_dao.deletePlayer(cp_dao.getPlayer(jerseyNumber));
    }

    public static CricketPlayer getPlayer(int jerseyNumber) {
        return cp_dao.getPlayer(jerseyNumber);
    }
}
