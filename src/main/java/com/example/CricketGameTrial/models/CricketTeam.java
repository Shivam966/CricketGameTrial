package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Document(indexName = "cricketteams")
public class CricketTeam {
    //"teamName" should be final because once a team gets a name it doesn't make sense to change that name
    @Id
    private String teamName;

    @Field(type = FieldType.Nested)
    Map<Integer, CricketPlayer> players = new HashMap<>();
}
