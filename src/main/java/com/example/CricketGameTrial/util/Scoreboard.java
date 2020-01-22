package com.example.CricketGameTrial.util;

import com.example.CricketGameTrial.service.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Scoreboard {
    private final int overs;
    private final String tossWinningTeam;
    private final String choseTo;
    private final Team one, two;
    private final String result;
}
