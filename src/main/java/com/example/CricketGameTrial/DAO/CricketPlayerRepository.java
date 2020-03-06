package com.example.CricketGameTrial.DAO;

import com.example.CricketGameTrial.models.CricketPlayer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CricketPlayerRepository extends ElasticsearchRepository<CricketPlayer, Integer> {

    @Override
    List<CricketPlayer> findAll();
}
