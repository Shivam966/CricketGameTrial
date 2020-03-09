package com.example.CricketGameTrial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Over {

    private final int bowlingPlayer;
    @JsonIgnore
    private int battingPlayer, nonStrikerBattingPlayer;

    private List<Character> balls;

    public Over(int battingPlayer, int nonStrikerBattingPlayer, int bowlingPlayer) {
        this.battingPlayer = battingPlayer;
        this.nonStrikerBattingPlayer = nonStrikerBattingPlayer;
        this.bowlingPlayer = bowlingPlayer;
        this.balls = new ArrayList<>();
    }

    public List<Character> getBalls() {
        return balls;
    }
}
