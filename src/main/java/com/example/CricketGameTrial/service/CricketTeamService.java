package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchDAO;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CricketTeamService {

    @Autowired
    CricketMatchDAO cm_dao;

    public void addTeam(CricketTeam team) {
        cm_dao.saveTeam(team);
    }

    public void removeTeam(String name) {
        cm_dao.deleteTeam(name);
    }

    public Map<String, CricketTeam> getAllTeams() {
        return cm_dao.getAllTeams();
    }

    public CricketTeam getTeam(String name) {
        return cm_dao.getTeam(name);
    }

    public void addPlayersToTeam(String name, CricketPlayer player) {
        cm_dao.addPlayersToTeam(name, player);
    }

    public CricketPlayer getPlayerFromTeam(String name,int index) {
        return cm_dao.getPlayerFromTeam(name,index);
    }

    public void removePlayersFromTeam(String name, int[] jerseyNumbers) {
        cm_dao.deletePlayersFromTeam(name, jerseyNumbers);
    }
}
