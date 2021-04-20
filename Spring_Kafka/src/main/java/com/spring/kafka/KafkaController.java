package com.spring.kafka;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController

public class KafkaController {

    @Autowired
    KafkaSenderI kafkaSenderI;

    @GetMapping("/v1/libraryevent")
    public ResponseEntity postLibraryEvent() throws Throwable {

        //invoke kafka producer
   //     libraryEvent.setLibraryEventType(LibraryEventType.NEW);
        kafkaSenderI.send("Test");
        return ResponseEntity.status(HttpStatus.CREATED).body("Test");
    }

   
}
