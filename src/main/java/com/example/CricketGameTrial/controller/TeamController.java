package com.example.CricketGameTrial.controller;

import com.example.CricketGameTrial.models.CricketPlayer;
import com.example.CricketGameTrial.models.CricketTeam;
import com.example.CricketGameTrial.service.CricketTeamService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class TeamController {

    @Autowired
    CricketTeamService cricketTeamService;

    @Autowired
    Client client;

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

    @GetMapping("/Team/{name}")
    public CricketTeam getTeam(@PathVariable String name) {
        return cricketTeamService.getTeam(name);
    }

    @GetMapping("/Team/stats/{name}")
    public List<Map<String, Aggregation>> searchByQuery(@PathVariable String name) {
        SearchResponse response = client.prepareSearch("cricketmatches")
                .setTypes("cricketmatch")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.boolQuery().should(QueryBuilders
                        .matchQuery("firstInnings.battingTeam", name)).should(QueryBuilders.matchQuery("firstInnings.bowlingTeam", name)))
                .addAggregation(AggregationBuilders.filters("won", QueryBuilders.matchQuery("result", name)))
                .addAggregation(AggregationBuilders.filters("tied", QueryBuilders.matchQuery("result", "tied")))
                .addAggregation(AggregationBuilders.filters("lost", QueryBuilders.boolQuery().mustNot(QueryBuilders.matchQuery("result", name))))
                .get()
        ;
        List<Map<String, Aggregation>> searchHits = Arrays.asList(response.getAggregations().getAsMap());
        return searchHits;
    }

}
