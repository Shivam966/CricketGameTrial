package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.service.CricketMatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


// By marking this class RestController, it is being made as a request handler
@RestController
public class MatchController {

    // RequestMapping and GetMapping is for making use of a method to handle a URI pattern
    @RequestMapping("/")
    public String Homepage() {
        return "This is the Home Page. For starting the match please write \"/startMatch?overs=...&team1=...&team2=..." +
                "\" after \"localhost:8080\"."+System.lineSeparator();
    }

    @GetMapping("/startMatch")
    public CricketMatch start(@RequestParam int overs, @RequestParam String team1, @RequestParam String team2) {
        CricketMatch m = new CricketMatch();
        m.setTeamName(team1,team2);
        m.setPlayers(m.getTeamA());
        m.setPlayers(m.getTeamB());
        return m.startMatch(overs);
    }
}
