package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketTeamRepository;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CricketTeamService {

    @Autowired
    CricketTeamRepository ct_dao;

    public void addTeam(CricketTeam team) {
        ct_dao.save(team);
    }

    public void removeTeam(String name) {
        ct_dao.deleteById(name);
    }

    public List<CricketTeam> getAllTeams() {
        return ct_dao.findAll();
    }

    public CricketTeam getTeam(String name) {
        return ct_dao.findById(name).get();
    }

    public void addPlayersToTeam(String name, CricketPlayer player) {
        CricketTeam team = this.getTeam(name);
        team.getPlayers().put(player.getJerseyNumber(), player);
        this.addTeam(team);
    }

    public void removePlayersFromTeam(String name, int[] jerseyNumbers) {
        for(int i=0;i<jerseyNumbers.length;i++) {
            CricketTeam team = this.getTeam(name);
            team.getPlayers().remove(jerseyNumbers[i]);
            this.addTeam(team);
        }
    }
}
