package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketMatch;

import java.util.ArrayList;
import java.util.List;

public class CricketMatchDAOImpl implements CricketMatchDAO {

    List<CricketMatch> matches;

    public CricketMatchDAOImpl() {
        matches = new ArrayList<>();
    }

    @Override
    public void createMatch(int numOfOvers, String team1, String team2) {
        CricketMatch match = new CricketMatch(numOfOvers,team1,team2);
        matches.add(match);
    }

    @Override
    public List<CricketMatch> getAllMatches() {
        return matches;
    }

    @Override
    public CricketMatch getMatch(int index) {
        return matches.get(index);
    }

}
