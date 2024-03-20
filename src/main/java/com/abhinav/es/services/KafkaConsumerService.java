package com.abhinav.es.services;

import com.abhinav.es.entity.MatchModel;
import com.abhinav.es.repository.Esrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
//    private AtomicInteger counter = new AtomicInteger(0);

    private Esrepo esRepo;
    @Autowired
    public KafkaConsumerService(Esrepo esRepo) {
        this.esRepo = esRepo;
    }

    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",topics = "ipl", groupId = "ipl-data")
    public void consume(MatchModel matchModel) {
        esRepo.save(matchModel);
        System.out.println("Match model saved to es");
        System.out.println("Received MatchModel: " + matchModel);
    }




}
