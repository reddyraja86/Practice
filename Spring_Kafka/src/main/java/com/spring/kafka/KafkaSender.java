package com.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaSender {
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	String kafkaTopic = "javainuse-topic";
	
	public void send(String message) {
	    
		ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send(kafkaTopic, message);
		
	    sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

	        @Override
	        public void onFailure(Throwable ex) {

	            handleFailure(kafkaTopic, message, ex);

	        }

	        @Override
	        public void onSuccess(SendResult<String, String> result) {

	            handleSuccess(kafkaTopic, message,result);

	        }
	    });

	}
	
	private void handleSuccess(String key, String value, SendResult<String, String> result) {
         //Audit Here
	    System.out.println("The record with key : {}, value : {} is produced sucessfullly to offset {}"+ key+value+ result.getRecordMetadata().offset());

	}

	private void handleFailure(String key, String value, Throwable ex) {

		 System.out.println("The record with key: {}, value: {} cannot be processed! caused by {}"+key+ value+ ex.getMessage());
	// Here you can implement the code to filter based on exception type and place the events on to a topic as the first approach or a database like the second approach.
	}
}