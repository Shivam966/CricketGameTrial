package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketMatch;
import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.service.CricketMatchService;
import com.example.CricketGameTrial.service.CricketPlayerService;
import com.example.CricketGameTrial.service.CricketTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


// By marking this class RestController, it is being made as a request handler
@RestController
public class MatchController {

    @Autowired
    CricketPlayerService cricketPlayerService;
    @Autowired
    CricketTeamService cricketTeamService;
    @Autowired
    CricketMatchService cricketMatchService;

    // RequestMapping and GetMapping is for making use of a method to handle a URI pattern
    @RequestMapping("/")
    public String Homepage() {
        return "This is the Home Page. For starting the match please write \"/startMatch?overs=...&team1=...&team2=..." +
                "\" after \"localhost:8080\"."+System.lineSeparator();
    }

    @PostMapping("/Player")
    public void createPlayer(@RequestBody CricketPlayer player){
        cricketPlayerService.addPlayer(player);
    }

    @PostMapping("/Team")
    public void addTeam(@RequestBody CricketTeam team) {
        cricketTeamService.addTeam(team);
    }

    @PutMapping("/PlayersToTeam/{name}/{jerseyNumbers}")
    public void addPlayersToTeam(@PathVariable String name, @PathVariable int[] jerseyNumbers) {
        cricketTeamService.addPlayersToTeam(name, jerseyNumbers);
    }

    @PostMapping("/startMatch")
    public CricketMatch start(@RequestBody CricketMatch match) {
        return cricketMatchService.startMatch(match);
    }

    @DeleteMapping("/Player/{jerseyNumber}")
    public void deletePlayer(@PathVariable int jerseyNumber) {
        cricketPlayerService.removePlayer(jerseyNumber);
    }

    @DeleteMapping("/Team/{name}")
    public void removeTeam(@PathVariable String name) {
        cricketTeamService.removeTeam(name);
    }

    @PutMapping("/PlayersFromTeam/{name}/{jerseyNumbers}")
    public void removePlayersFromTeam(@PathVariable String name, @PathVariable int[] jerseyNumbers) {
        cricketTeamService.removePlayersFromTeam(name,jerseyNumbers);
    }

    @GetMapping("/AllPlayers")
    public Map<Integer, CricketPlayer> getAllPlayers() {
        return cricketPlayerService.getAllPlayers();
    }

    @GetMapping("/Player/{jerseyNumber}")
    public CricketPlayer getPlayer(@PathVariable int jerseyNumber) {
        return cricketPlayerService.getPlayer(jerseyNumber);
    }

    @GetMapping("/AllTeams")
    public Map<String, CricketTeam> getAllTeams() {
        return cricketTeamService.getAllTeams();
    }

    @GetMapping("/PlayerFromTeam/{name}/{index}")
    public CricketPlayer getPlayerFromTeam(@PathVariable String name, @PathVariable int index) {
        return cricketTeamService.getPlayerFromTeam(name,index);
    }

    @GetMapping("/Team/{name}")
    public CricketTeam getTeam(@PathVariable String name) {
        return cricketTeamService.getTeam(name);
    }

    @GetMapping("/AllMatches")
    public Map<Integer, CricketMatch> getAllMatches() {
        return cricketMatchService.getAllMatches();
    }
}
