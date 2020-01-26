package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketMatch;
import java.util.List;

public interface CricketMatchDAO {
    void createMatch(int numOfOvers, String team1, String team2);
    List<CricketMatch> getAllMatches();
    CricketMatch getMatch(int index);
}
