package com.spring.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyConsumer {
	
	@KafkaListener(topics = {"javainuse-topic"})
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
		
		System.out.println("Consumer Record :{} "+consumerRecord);
		
	}
	

}
