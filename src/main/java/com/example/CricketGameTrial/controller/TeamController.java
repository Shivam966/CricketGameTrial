package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.service.CricketTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeamController {

    @Autowired
    CricketTeamService cricketTeamService;

    @PostMapping("/Team")
    public void addTeam(@RequestBody CricketTeam team) {
        cricketTeamService.addTeam(team);
    }

    @PostMapping("/PlayersToTeam/{name}")
    public void addPlayersToTeam(@PathVariable String name, @RequestBody CricketPlayer player) {
        cricketTeamService.addPlayersToTeam(name, player);
    }

    @DeleteMapping("/Team/{name}")
    public void removeTeam(@PathVariable String name) {
        cricketTeamService.removeTeam(name);
    }

    @PutMapping("/PlayersFromTeam/{name}/{jerseyNumbers}")
    public void removePlayersFromTeam(@PathVariable String name, @PathVariable int[] jerseyNumbers) {
        cricketTeamService.removePlayersFromTeam(name,jerseyNumbers);
    }

    @GetMapping("/AllTeams")
    public List<CricketTeam> getAllTeams() {
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

}
