package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerBattingScoreCard {
    private String playerName;
    private int jerseyNumber, playerAge;
    private int playerRating;
    CricketPlayer.type playerType;
    private float playerStrike_rate;
    private int RunsScored, BallsPlayed;
    private int NumOfFours, NumOfSixes;

    public PlayerBattingScoreCard(CricketPlayer player) {
        playerName = player.getPlayerName();
        jerseyNumber = player.getJerseyNumber();
        playerAge = player.getPlayerAge();
        playerRating = player.getPlayerRating();
        playerType = player.getPlayerType();
        playerStrike_rate = player.getPlayerWholeStrike_rate();
        RunsScored = player.getWholeRunsScored();
        BallsPlayed = player.getWholeBallsPlayed();
        NumOfFours = player.getWholeNumOfFours();
        NumOfSixes = player.getWholeNumOfSixes();
    }

    public PlayerBattingScoreCard(CricketPlayer player, int matchID) {
        playerName = player.getPlayerName();
        jerseyNumber = player.getJerseyNumber();
        playerAge = player.getPlayerAge();
        playerRating = player.getPlayerRating();
        playerType = player.getPlayerType();
        playerStrike_rate = player.getPlayerStats().get(matchID).getPlayerStrike_rate();
        RunsScored = player.getPlayerStats().get(matchID).getRunsScored();
        BallsPlayed = player.getPlayerStats().get(matchID).getBallsPlayed();
        NumOfFours = player.getPlayerStats().get(matchID).getNumOfFours();
        NumOfSixes = player.getPlayerStats().get(matchID).getNumOfSixes();
    }
}
