package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketTeam;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CricketTeamRepository extends ElasticsearchRepository<CricketTeam, String> {

    @Override
    List<CricketTeam> findAll();
}
