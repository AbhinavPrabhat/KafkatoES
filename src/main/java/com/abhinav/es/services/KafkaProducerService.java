package com.abhinav.es.services;

import com.abhinav.es.entity.MatchModel;
import org.apache.kafka.clients.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.List;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, MatchModel> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, MatchModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(List<MatchModel> matchModels) {
        for (MatchModel matchModel : matchModels) {
            kafkaTemplate.send("ipl", matchModel.getId(), matchModel);
        }
    }
}
