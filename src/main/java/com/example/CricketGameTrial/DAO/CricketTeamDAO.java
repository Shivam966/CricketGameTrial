package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;

import java.util.Map;

public interface CricketTeamDAO {

    Map<String, CricketTeam> getAllTeams();
    CricketTeam getTeam(String name);
    void addPlayersToTeam(String name, int[] jerseyNumbers);
    CricketPlayer getPlayerFromTeam(String name, int index);
    void deletePlayersFromTeam(String name, int[] jerseyNumbers);
    void saveTeam(CricketTeam team);
    void deleteTeam(CricketTeam team);
}
