package com.spring.kafka;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class AppEvents {
	
    @Autowired
    KafkaSenderI kafkaSenderI;


    @EventListener(ApplicationReadyEvent.class)
    public void startApp() throws Throwable {

        
    	System.out.println("--------ApplicationReadyEvent-----------");
    	kafkaSenderI.send("Test from app start");
    }
}