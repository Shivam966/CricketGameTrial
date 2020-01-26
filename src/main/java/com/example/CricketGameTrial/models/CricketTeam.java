package com.example.CricketGameTrial.models;

import com.example.CricketGameTrial.models.CricketPlayer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CricketTeam {
    //"name" should be final because once a team gets a name it doesn't make sense to change that name
    private final String name;

    // Assuming right now that each team has only 11 players and no substitutes
    List<CricketPlayer> players;

    public void setEconomyAndStrikeRate() {

    }

    public CricketTeam(String name) {
        this.name = name;
        players = new ArrayList<>();
    }
}
