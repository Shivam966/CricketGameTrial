package com.example.CricketGameTrial.util;

import com.example.CricketGameTrial.service.Over;
import com.example.CricketGameTrial.service.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class Scoreboard {
    private final int overs;
    private final String tossWinningTeam;
    private final String choseTo;
    private final Team one, two;
    private ArrayList<Over> firstInningsOver, secondInningsOver;
    private final String result;
}
