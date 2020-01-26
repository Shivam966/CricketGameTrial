package com.example.CricketGameTrial.models;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.models.Over;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/* Innings class is kept abstract because it does not make sense to make instance of innings class, it's purpose is to
   impose protocol for its subclasses
   It will have 2 teams - batting team and bowling team that will refer to original team objects
   During toss, setter of batting team and bowling team will be called
   start() method has been made abstract because first innings and seconds innings will have different implementations
   of start, latter will have target
*/
@Getter
@Setter
public class Innings {

    private CricketTeam battingTeam, bowlingTeam;
    private CricketPlayer battingPlayer, nonStrikerBattingPlayer, bowlingPlayer;
    private int runs, wickets;
    private ArrayList<Over> overs;

    public Innings() {
        overs = new ArrayList<>();
    }
}
