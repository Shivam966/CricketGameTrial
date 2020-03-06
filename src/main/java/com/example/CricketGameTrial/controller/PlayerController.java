package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.PlayerBattingScoreCard;
import com.example.CricketGameTrial.models.PlayerBowlingScoreCard;
import com.example.CricketGameTrial.service.CricketPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    @Autowired
    CricketPlayerService cricketPlayerService;

    @GetMapping("/AllPlayers")
    public List<CricketPlayer> getAllPlayers() {
        return cricketPlayerService.getAllPlayers();
    }

    @GetMapping("/Player/{jerseyNumber}")
    public CricketPlayer getPlayer(@PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayer(jerseyNumber);
    }

    @GetMapping("/PlayerWholeBattingStats/{jerseyNumber}")
    public PlayerBattingScoreCard getPlayerWholeBattingStats(@PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayerWholeBattingStats(jerseyNumber);
    }

    @GetMapping("/PlayerWholeBowlingStats/{jerseyNumber}")
    public PlayerBowlingScoreCard getPlayerWholeBowlingStats(@PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayerWholeBowlingStats(jerseyNumber);
    }

    @GetMapping("/PlayerBattingStats/{jerseyNumber}/{matchID}")
    public PlayerBattingScoreCard getPlayerBattingStats(@PathVariable int jerseyNumber, @PathVariable int matchID) {
        return cricketPlayerService.getPlayerBattingStats(jerseyNumber, matchID);
    }

    @GetMapping("/PlayerBowlingStats/{jerseyNumber}/{matchID}")
    public PlayerBowlingScoreCard getPlayerBowlingStats(@PathVariable int jerseyNumber, @PathVariable int matchID) {
        return cricketPlayerService.getPlayerBowlingStats(jerseyNumber,matchID);
    }
}
