package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private String battingTeam, bowlingTeam;
    private int runs, wickets;
    private List<Over> overs;

    public Innings() {
        overs = new ArrayList<>();
    }
}
