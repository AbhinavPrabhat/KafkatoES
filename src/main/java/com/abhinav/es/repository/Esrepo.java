package com.abhinav.es.repository;

import com.abhinav.es.entity.MatchModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Esrepo extends ElasticsearchRepository<MatchModel,String>{




}
