package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.DAO.CricketMatchRepository;
import com.example.CricketGameTrial.DAO.CricketPlayerRepository;
import com.example.CricketGameTrial.DAO.CricketTeamRepository;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CricketTeamService {

    @Autowired
    CricketTeamRepository ct_dao;

    @Autowired
    CricketPlayerRepository cp_dao;

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
        ct_dao.findById(name).get().getPlayers().add(player.getJerseyNumber());
        ct_dao.save(ct_dao.findById(name).get());
        cp_dao.save(player);
    }

    public CricketPlayer getPlayerFromTeam(String name,int index) {
        int playerJerseyNumber = ct_dao.findById(name).get().getPlayers().get(index);
        return cp_dao.findById(playerJerseyNumber).get();
    }

    public void removePlayersFromTeam(String name, int[] jerseyNumbers) {
        for(int i=0;i<jerseyNumbers.length;i++) {
            ct_dao.findById(name).get().getPlayers().remove(jerseyNumbers[i]);
            cp_dao.deleteById(jerseyNumbers[i]);
        }
    }
}
