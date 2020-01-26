package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.Innings;
import com.example.CricketGameTrial.models.Over;

abstract class InningsService {

    static class PlayerWrapper{
        CricketPlayer player;

        PlayerWrapper(CricketPlayer player) {
            this.player = player;
        }
    }

    // start method takes number of overs to be played as an argument because innings can only start by mentioning
    // number of overs to be played in it and returns number of overs played by the batting team
    public static void start(Innings innings, int numOfOvers) {
        // balls represents number of balls to be played
        int count = 0; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        innings.setBattingPlayer(innings.getBattingTeam().getPlayers().get(0));
        innings.setNonStrikerBattingPlayer(innings.getBattingTeam().getPlayers().get(1));
        innings.setBowlingPlayer(innings.getBowlingTeam().getPlayers().get(index));

        while(count<numOfOvers && innings.getWickets()<10) {
            innings.getOvers().add(new Over());
            int runsInOver = OverService.start(innings);

            if(innings.getOvers().get(innings.getOvers().size()-1).getBalls().size()==6 && runsInOver==0)
                innings.getBowlingPlayer().addNumOfMaidenOvers();

            if(index==10) index = 6;
            else index++;
            innings.setBowlingPlayer(innings.getBowlingTeam().getPlayers().get(index));

            PlayerWrapper pw1 = new PlayerWrapper(innings.getBattingPlayer()),
                    pw2 = new PlayerWrapper(innings.getNonStrikerBattingPlayer());
            swap(pw1,pw2);
            innings.setBattingPlayer(pw1.player);
            innings.setNonStrikerBattingPlayer(pw2.player);
            count++;
        }
    }

    public static void start(Innings innings, int numOfOvers, int target) {
        // balls represents number of balls to be played
        int count = 0; // count is counter for while loop to check numOfOvers and ran will be used for random
        int index = 6;
        innings.setBattingPlayer(innings.getBattingTeam().getPlayers().get(0));
        innings.setNonStrikerBattingPlayer(innings.getBattingTeam().getPlayers().get(1));
        innings.setBowlingPlayer(innings.getBowlingTeam().getPlayers().get(index));

        while(count<numOfOvers && innings.getWickets()<10 && innings.getRuns()<=target) {
            innings.getOvers().add(new Over());
            int runsInOver = OverService.start(innings);

            if(innings.getOvers().get(innings.getOvers().size()-1).getBalls().size()==6 && runsInOver==0)
                innings.getBowlingPlayer().addNumOfMaidenOvers();

            if(index==10) index = 6;
            else index++;
            innings.setBowlingPlayer(innings.getBowlingTeam().getPlayers().get(index));

            PlayerWrapper pw1 = new PlayerWrapper(innings.getBattingPlayer()),
                    pw2 = new PlayerWrapper(innings.getNonStrikerBattingPlayer());
            swap(pw1,pw2);
            innings.setBattingPlayer(pw1.player);
            innings.setNonStrikerBattingPlayer(pw2.player);
            count++;
        }
    }

    static void swap(PlayerWrapper pw1, PlayerWrapper pw2) {
        CricketPlayer temp = pw1.player;
        pw1.player = pw2.player;
        pw2.player = temp;
    }
}
