package com.example.CricketGameTrial.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;


//   Innings will have 2 teams - batting team and bowling team
//   During toss, setter of batting team and bowling team will be called

@Getter
@Setter
public class Innings {

    private String battingTeam, bowlingTeam;
    private int runs, wickets;
    @Field(type = FieldType.Nested)
    private List<Over> overs;

    public Innings() {
        overs = new ArrayList<>();
    }
}
