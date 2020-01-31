package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;


@Component
public class CricketMatchDAOImpl implements CricketMatchDAO {

    private Map<Integer, CricketMatch> matches;
    private Map<String, CricketTeam> teams;
    private Map<Integer, CricketPlayer> players;

    public CricketMatchDAOImpl() {
        matches = new HashMap<>();
        players = new HashMap<>();
        teams = new HashMap<>();
    }

    @Override
    public void createMatch(CricketMatch match) {
        matches.put(match.getMatchID(),match);
    }

    @Override
    public Map<Integer, CricketMatch> getAllMatches() {
        return matches;
    }

    @Override
    public CricketMatch getMatch(int index) {
        return matches.get(index);
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
    public Map<String, CricketTeam> getAllTeams() {
        return teams;
    }

    @Override
    public CricketTeam getTeam(String name) {
        return teams.get(name);
    }

    @Override
    public void addPlayersToTeam(String name, CricketPlayer player) {
        players.put(player.getJerseyNumber(),player);
        teams.get(name).getPlayers().add(player.getJerseyNumber());
    }

    @Override
    public CricketPlayer getPlayerFromTeam(String name, int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= teams.get(name).getPlayers().size()) throw new IndexOutOfBoundsException();
        return players.get(teams.get(name).getPlayers().get(index));
    }

    @Override
    public void deletePlayersFromTeam(String name, int[] jerseyNumbers) {
        for(int jerseyNumber:jerseyNumbers) {
            teams.get(name).getPlayers().removeIf(num->num.equals(jerseyNumber));
            players.remove(jerseyNumber);
        }
    }

    @Override
    public void saveTeam(CricketTeam team) {
        teams.put(team.getTeamName(), team);
    }

    @Override
    public void deleteTeam(String team) {
        teams.remove(team);
    }

}
