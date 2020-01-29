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
            ran = (int) (Math.random() * 8);

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
            c++;
        }
        return runsInOver;
    }

    public int startOver(Innings innings, int target, int matchID) {
        int ran;
        int runsInOver = 0;
        int c = 0;
        while(c < 6 && innings.getWickets()<10 && innings.getRuns()<=target) {
            ran = (int) (Math.random() * 8);

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
            c++;
        }
        return runsInOver;
    }


}
