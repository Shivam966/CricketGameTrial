package com.example.CricketGameTrial.util;

import com.example.CricketGameTrial.domain.FirstInnings;
import com.example.CricketGameTrial.domain.SecondInnings;
import com.example.CricketGameTrial.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.DecimalFormat;

@AllArgsConstructor
@Getter
public class Scoreboard {
    int overs;
    String tossWinningTeam;
    String choseTo;
    Team one, two;
    String result;
}
