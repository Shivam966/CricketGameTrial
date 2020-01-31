package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.service.CricketMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


// By marking this class RestController, it is being made as a request handler
@RestController
public class MatchController {

    @Autowired
    CricketMatchService cricketMatchService;

    @PostMapping("/startMatch")
    public CricketMatch start(@RequestBody CricketMatch match) {
        return cricketMatchService.startMatch(match);
    }

    @GetMapping("/AllMatches")
    public Map<Integer, CricketMatch> getAllMatches() {
        return cricketMatchService.getAllMatches();
    }

    @GetMapping("/Match/{matchID}")
    public CricketMatch getMatch(@PathVariable int matchID){ return cricketMatchService.getMatch(matchID);}
}
