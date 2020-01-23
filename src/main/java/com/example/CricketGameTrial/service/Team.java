package com.example.CricketGameTrial.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Team {
    //"name" should be final because once a team gets a name it doesn't make sense to change that name
    private final String name;

    // Each team will have their runs and wickets when they are batting
    private int runs;
    private int wickets;

    // Assuming right now that each team has only 11 players and no substitutes
    Player[] players = new Player[11];

    public Player getPlayer(int index) throws IndexOutOfBoundsException {
        if(index>=11 || index<0) throw new IndexOutOfBoundsException();
        return players[index];
    }

    public void setEconomyAndStrikeRate() {
        for(int i = 0;i < this.getPlayers().length; i++) {

            if(this.getPlayer(i).getBallsBowled()!=0)  this
                    .getPlayer(i).setEconomy(Math.round((double)this.getPlayer(i).getRunsGiven()/
                            this.getPlayer(i).getBallsBowled()*600.0)/100.0);

            if(this.getPlayer(i).getBallsPlayed()!=0) this.getPlayer(i)
                    .setStrike_rate(Math.round((double)this.getPlayer(i).getRunsScored()/
                            this.getPlayer(i).getBallsPlayed()*10000.0)/100.0);
        }
    }

    public Team(String name) {
        this.name = name;
    }
}
