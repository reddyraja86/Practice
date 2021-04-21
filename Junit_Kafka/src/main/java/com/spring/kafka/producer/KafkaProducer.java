package com.spring.kafka.producer;

import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public ListenableFuture<SendResult<String,String>> send(String topic, String payload) {
        LOGGER.info("========================sending payload='{}' to topic='{}'", payload, topic);
        
        
        ListenableFuture<SendResult<String,String>> listenableFuture =  kafkaTemplate.send(topic, payload);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
              System.out.println("----fail-------");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
            	System.out.println("----onSuccess-------");
            }
        });
        
        return listenableFuture;
    }
}