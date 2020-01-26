package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.service.CricketPlayerService;

import java.util.HashMap;
import java.util.Map;

public class CricketTeamDAOImpl implements CricketTeamDAO {

    private Map<String, CricketTeam> teams;

    public CricketTeamDAOImpl() {
        teams = new HashMap<>();
    }

    @Override
    public Map<String, CricketTeam> getAllTeams() {
        return teams;
    }

    @Override
    public CricketTeam getTeam(String name) {
        return teams.get(name);
    }

    @Override
    public void addPlayersToTeam(String name, int[] jerseyNumbers) {
        for(int jerseyNumber:jerseyNumbers) {
            if(CricketPlayerService.getPlayer(jerseyNumber).getTeam().equals(name))
                teams.get(name).getPlayers().add(CricketPlayerService.getPlayer(jerseyNumber));
        }
    }

    @Override
    public CricketPlayer getPlayerFromTeam(String name, int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= teams.get(name).getPlayers().size()) throw new IndexOutOfBoundsException();
        return teams.get(name).getPlayers().get(index);
    }

    @Override
    public void deletePlayersFromTeam(String name, int[] jerseyNumbers) {
        for(int jerseyNumber:jerseyNumbers) {
            teams.get(name).getPlayers().remove(CricketPlayerService.getPlayer(jerseyNumber));
        }
    }

    @Override
    public void saveTeam(CricketTeam team) {
        teams.put(team.getName(), team);
    }

    @Override
    public void deleteTeam(CricketTeam team) {
        teams.remove(team.getName());
    }
}
