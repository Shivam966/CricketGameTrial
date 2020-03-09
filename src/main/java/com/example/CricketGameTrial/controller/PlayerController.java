package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.PlayerBattingScoreCard;
import com.example.CricketGameTrial.models.PlayerBowlingScoreCard;
import com.example.CricketGameTrial.service.CricketPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    @Autowired
    CricketPlayerService cricketPlayerService;

    @GetMapping("/{teamName}/AllPlayers")
    public Collection<CricketPlayer> getAllPlayersFromTeam(@PathVariable String teamName) {
        return cricketPlayerService.getAllPlayersFromTeam(teamName);
    }

    @GetMapping("/{teamName}/Player/{jerseyNumber}")
    public CricketPlayer getPlayerFromTeam(@PathVariable String teamName,@PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayerFromTeam(teamName, jerseyNumber);
    }

    @GetMapping("/{teamName}/PlayerWholeBattingStats/{jerseyNumber}")
    public PlayerBattingScoreCard getPlayerWholeBattingStats(@PathVariable String teamName,
                                                             @PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayerWholeBattingStatsFromTeam(teamName, jerseyNumber);
    }

    @GetMapping("/{teamName}/PlayerWholeBowlingStats/{jerseyNumber}")
    public PlayerBowlingScoreCard getPlayerWholeBowlingStats(@PathVariable String teamName,
                                                             @PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayerWholeBowlingStatsFromTeam(teamName, jerseyNumber);
    }

    @GetMapping("/{teamName}/PlayerBattingStats/{jerseyNumber}/{matchID}")
    public PlayerBattingScoreCard getPlayerBattingStats(@PathVariable String teamName, @PathVariable int jerseyNumber,
                                                        @PathVariable int matchID) {
        return cricketPlayerService.getPlayerBattingStatsFromTeam(teamName, jerseyNumber, matchID);
    }

    @GetMapping("/{teamName}/PlayerBowlingStats/{jerseyNumber}/{matchID}")
    public PlayerBowlingScoreCard getPlayerBowlingStats(@PathVariable String teamName, @PathVariable int jerseyNumber,
                                                        @PathVariable int matchID) {
        return cricketPlayerService.getPlayerBowlingStatsFromTeam(teamName, jerseyNumber,matchID);
    }
}
