package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;

import java.util.Map;

public interface CricketMatchDAO {
    void createMatch(CricketMatch match);
    Map<Integer, CricketMatch> getAllMatches();
    CricketMatch getMatch(int index);

    Map<Integer, CricketPlayer> getAllPlayers();
    CricketPlayer getPlayer(int jerseyNumber);
    void savePlayer(CricketPlayer player);
    void deletePlayer(CricketPlayer player);

    Map<String, CricketTeam> getAllTeams();
    CricketTeam getTeam(String name);
    void addPlayersToTeam(String name, int[] jerseyNumbers);
    CricketPlayer getPlayerFromTeam(String name, int index);
    void deletePlayersFromTeam(String name, int[] jerseyNumbers);
    void saveTeam(CricketTeam team);
    void deleteTeam(CricketTeam team);
}
