package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketTeamDAO;
import com.example.CricketGameTrial.DAO.CricketTeamDAOImpl;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;

import java.util.Map;

public abstract class CricketTeamService {
    private static CricketTeamDAO ct_dao = new CricketTeamDAOImpl();

    public static void addTeam(String name) {
        ct_dao.saveTeam(new CricketTeam(name));
    }

    public static void removeTeam(String name) {
        ct_dao.deleteTeam(ct_dao.getAllTeams().get(name));
    }

    public static Map<String, CricketTeam> getAllTeams() {
        return ct_dao.getAllTeams();
    }

    public static CricketTeam getTeam(String name) {
        return ct_dao.getTeam(name);
    }

    public static void addPlayersToTeam(String name, int[] jerseyNumbers) {
        ct_dao.addPlayersToTeam(name, jerseyNumbers);
    }

    public static CricketPlayer getPlayerFromTeam(String name,int index) {
        return ct_dao.getPlayerFromTeam(name,index);
    }

    public static void removePlayersFromTeam(String name, int[] jerseyNumbers) {
        ct_dao.deletePlayersFromTeam(name, jerseyNumbers);
    }
}
