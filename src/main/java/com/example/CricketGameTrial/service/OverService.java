package com.example.CricketGameTrial.service;

import com.example.CricketGameTrial.models.Innings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class OverService {

    @Autowired
    CricketPlayerService cricketPlayerService;

    public int startOver(Innings innings, int matchID) {
        int ran;
        int runsInOver = 0;
        int c = 0;
        while(c < 6 && innings.getWickets()<10) {
            ran = generateRunsByRating(innings, cricketPlayerService.getPlayer(innings.getOvers().get(innings
                    .getOvers().size()-1).getBattingPlayer()).getPlayerRating());

            // Here "7" is considered as wicket
            cricketPlayerService.updateBallsToStats(innings,matchID);
            if (ran == 7) {
                cricketPlayerService.doWhenRunIsSeven(innings,matchID);
            } else {
                innings.setRuns(innings.getRuns()+ran);
                runsInOver += ran;

                cricketPlayerService.updateRunsToStats(innings, matchID, ran);

                if(ran==4) cricketPlayerService.doWhenRunIsFour(innings,matchID);
                if(ran==6) cricketPlayerService.doWhenRunIsSix(innings,matchID);
            }
            if(ran<7) innings.getOvers().get(innings.getOvers().size()-1).getBalls().add(Character.forDigit(ran,
                    10));
            else innings.getOvers().get(innings.getOvers().size()-1).getBalls().add('W');
            if(ran%2!=0) doWhenRunIsOdd(innings);
            c++;
        }
        return runsInOver;
    }

    public int startOver(Innings innings, int target, int matchID) {
        int ran;
        int runsInOver = 0;
        int c = 0;
        while(c < 6 && innings.getWickets()<10 && innings.getRuns()<=target) {
            ran = generateRunsByRating(innings, cricketPlayerService.getPlayer(innings.getOvers().get(innings
                    .getOvers().size()-1).getBattingPlayer()).getPlayerRating());

            // Here "7" is considered as wicket
            cricketPlayerService.updateBallsToStats(innings,matchID);
            if (ran == 7) {
                cricketPlayerService.doWhenRunIsSeven(innings,matchID);
            } else {
                innings.setRuns(innings.getRuns()+ran);
                runsInOver += ran;

                cricketPlayerService.updateRunsToStats(innings,matchID,ran);

                if(ran==4) cricketPlayerService.doWhenRunIsFour(innings,matchID);
                if(ran==6) cricketPlayerService.doWhenRunIsSix(innings,matchID);
            }
            if(ran<7) innings.getOvers().get(innings.getOvers().size()-1).getBalls().add(Character.forDigit(ran,
                    10));
            else innings.getOvers().get(innings.getOvers().size()-1).getBalls().add('W');
            if(ran%2!=0) doWhenRunIsOdd(innings);
            c++;
        }
        return runsInOver;
    }

    int generateRunsByRating(Innings innings, int rating) {
        int ran;
        float probability = (float)Math.random();
        if(probability > ((float)rating/100.0f)) ran = 7;
        else {
            ran = (int)(Math.random()*7);
        }
        return ran;
    }

    void doWhenRunIsOdd(Innings innings) {
        int battingPlayer = innings.getOvers().get(innings.getOvers().size()-1).getBattingPlayer();
        int nonStrikerBattingPlayer = innings.getOvers().get(innings.getOvers().size()-1).getNonStrikerBattingPlayer();
        innings.getOvers().get(innings.getOvers().size()-1).setBattingPlayer(nonStrikerBattingPlayer);
        innings.getOvers().get(innings.getOvers().size()-1).setNonStrikerBattingPlayer(battingPlayer);
    }
}
