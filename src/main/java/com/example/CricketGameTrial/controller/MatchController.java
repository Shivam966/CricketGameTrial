package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.service.CricketMatchService;
import com.example.CricketGameTrial.service.CricketPlayerService;
import com.example.CricketGameTrial.service.CricketTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


// By marking this class RestController, it is being made as a request handler
@RestController
public class MatchController {

    // RequestMapping and GetMapping is for making use of a method to handle a URI pattern
    @RequestMapping("/")
    public String Homepage() {
        return "This is the Home Page. For starting the match please write \"/startMatch?overs=...&team1=...&team2=..." +
                "\" after \"localhost:8080\"."+System.lineSeparator();
    }

    @PostMapping("/createPlayer")
    public void createPlayer(@RequestParam String name,@RequestParam String team ,@RequestParam int jerseyNumber,
                             @RequestParam int age) {
        CricketPlayerService.addPlayer(name,team,jerseyNumber,age);
    }

    @PostMapping("/deletePlayer")
    public void deletePlayer(@RequestParam int jerseyNumber) {
        CricketPlayerService.removePlayer(jerseyNumber);
    }

    @PostMapping("/addTeam")
    public void addTeam(@RequestParam String name) {
        CricketTeamService.addTeam(name);
    }

    @PostMapping("/removeTeam")
    public void removeTeam(@RequestParam String name) {
        CricketTeamService.removeTeam(name);
    }

    @PostMapping("/addPlayersToTeam")
    public void addPlayersToTeam(@RequestParam String name, @RequestParam int[] jerseyNumbers) {
        CricketTeamService.addPlayersToTeam(name, jerseyNumbers);
    }

    @PostMapping("/removePlayersFromTeam")
    public void removePlayersFromTeam(@RequestParam String name, @RequestParam int[] jerseyNumbers) {
        CricketTeamService.removePlayersFromTeam(name,jerseyNumbers);
    }

    @PostMapping("/startMatch")
    public CricketMatch start(@RequestParam int overs, @RequestParam String team1, @RequestParam String team2) {
        return CricketMatchService.startMatch(overs,team1,team2);
    }

    @GetMapping("/getAllPlayers")
    public Map<Integer, CricketPlayer> getAllPlayers() {
        return CricketPlayerService.getAllPlayers();
    }

    @GetMapping("/getPlayer")
    public CricketPlayer getPlayer(@RequestParam int jerseyNumber) {
        return CricketPlayerService.getPlayer(jerseyNumber);
    }

    @GetMapping("/getAllTeams")
    public Map<String, CricketTeam> getAllTeams() {
        return CricketTeamService.getAllTeams();
    }

    @GetMapping("/getPlayerFromTeam")
    public CricketPlayer getPlayerFromTeam(@RequestParam String name, @RequestParam int index) {
        return CricketTeamService.getPlayerFromTeam(name,index);
    }

    @GetMapping("/getTeam")
    public CricketTeam getTeam(@RequestParam String name) {
        return CricketTeamService.getTeam(name);
    }

    @GetMapping("/getAllMatches")
    public List<CricketMatch> getAllMatches() {
        return CricketMatchService.getAllMatches();
    }
}
