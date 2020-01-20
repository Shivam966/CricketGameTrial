package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.domain.Match;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


// By marking this class RestController, it is being made as a request handler
@RestController
public class MatchController {

    Match m = new Match();

    // RequestMapping and GetMapping is for making use of a method to handle a URI pattern
    @RequestMapping("/")
    public String Homepage() {
        return "This is the Home Page. For starting the match please write \"/startMatch\" after \"localhost:8080\"."+System.lineSeparator();
    }

    @GetMapping("/startMatch")
    public String start() {
        return m.startMatch();
    }
}
