package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(indexName = "cricketteams", shards = 1, replicas = 0)
public class CricketTeam {
    //"teamName" should be final because once a team gets a name it doesn't make sense to change that name
    @Id
    private String teamName;

    List<Integer> players = new ArrayList<>();
}
