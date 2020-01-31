package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerBowlingScoreCard {
    private final String playerName;
    private final int jerseyNumber, playerAge;
    private int playerRating;
    CricketPlayer.type playerType;
    private float playerEconomy;
    private int BallsBowled, WicketsTaken, RunsGiven;
    private int NumOfMaidenOvers;

    public PlayerBowlingScoreCard(CricketPlayer player) {
        playerName = player.getPlayerName();
        jerseyNumber = player.getJerseyNumber();
        playerAge = player.getPlayerAge();
        playerRating = player.getPlayerRating();
        playerType = player.getPlayerType();
        playerEconomy = player.getPlayerWholeEconomy();
        BallsBowled = player.getWholeBallsBowled();
        RunsGiven = player.getWholeRunsGiven();
        WicketsTaken = player.getWholeWicketsTaken();
        NumOfMaidenOvers = player.getWholeNumOfMaidenOvers();
    }

    public PlayerBowlingScoreCard(CricketPlayer player, int matchID) {
        playerName = player.getPlayerName();
        jerseyNumber = player.getJerseyNumber();
        playerAge = player.getPlayerAge();
        playerRating = player.getPlayerRating();
        playerType = player.getPlayerType();
        playerEconomy = player.getPlayerStats().get(matchID).getPlayerEconomy();
        BallsBowled = player.getPlayerStats().get(matchID).getBallsBowled();
        RunsGiven = player.getPlayerStats().get(matchID).getRunsGiven();
        WicketsTaken = player.getPlayerStats().get(matchID).getWicketsTaken();
        NumOfMaidenOvers = player.getPlayerStats().get(matchID).getNumOfMaidenOvers();
    }
}
