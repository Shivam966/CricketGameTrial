package com.example.CricketGameTrial.models;

// Player class will contain details of each player
// Right now basic details have been added, other variables like height, weight can be added if needed

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "cricketplayers", shards = 1, replicas = 0)
public class CricketPlayer {
    enum type {
        BATSMAN, BOWLER, ALLROUNDER
    }
    private String playerName;
    @Id
    private int jerseyNumber;
    private int playerAge;
    private int playerRating;  // playerRating will be out of 100
    type playerType;
    private float playerWholeEconomy, playerWholeStrike_rate;
    private int wholeRunsScored, wholeBallsPlayed, wholeBallsBowled, wholeWicketsTaken, wholeRunsGiven;
    private int wholeNumOfFours, wholeNumOfSixes, wholeNumOfMaidenOvers;
    private Map<Integer, Stats> playerStats = new HashMap<>();

    public void addWholeRunsScored(int runs) {
        wholeRunsScored+=runs;
    }

    public void addWholeWicketsTaken() {
        wholeWicketsTaken++;
    }

    public void addWholeBallsPlayed() {
        wholeBallsPlayed++;
    }

    public void addWholeNumOfFours() {
        wholeNumOfFours++;
    }

    public void addWholeNumOfSixes() {
        wholeNumOfSixes++;
    }

    public void addWholeNumOfMaidenOvers() {
        wholeNumOfMaidenOvers++;
    }

    public void addWholeBallsBowled() {
        wholeBallsBowled++;
    }

    public void addWholeRunsGiven(int runsGiven) {
        this.wholeRunsGiven+=runsGiven;
    }

//    public CricketPlayer(String name, int jerseyNumber, int age, int playerRating) {
//        playerName = name;
//        this.jerseyNumber = jerseyNumber;
//        playerAge = age;
//        this.playerRating = playerRating;
//    }
}
