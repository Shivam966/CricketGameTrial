package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CricketTeam {
    //"teamName" should be final because once a team gets a name it doesn't make sense to change that name
    private String teamName;

    List<Integer> players = new ArrayList<>();
}
