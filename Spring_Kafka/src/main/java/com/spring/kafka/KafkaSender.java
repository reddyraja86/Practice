package com.spring.kafka;

import java.util.logging.Logger;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.event.ContainerStoppedEvent;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaSender implements KafkaSenderI {

	private static final Logger log = Logger.getLogger(KafkaSender.class.getName());

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaSenderI kafkaSenderI;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	String kafkaTopic = "javainuse-topic";

	@Override
	public void send(String message) throws Throwable {

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(kafkaTopic, "Collection_Name",
				message);
		System.out.println("---send------");

		ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send(record);

//		if (message.length() > 0)
//			throw new KafkaProducerException(record, message, null);

		sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onFailure(Throwable ex) {

				try {
					handleFailure(kafkaTopic, message, ex);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onSuccess(SendResult<String, String> result) {

				handleSuccess(kafkaTopic, message, result);

			}
		});

	}

	@Override
	public String failedRetry(Throwable e) {
		log.info("---------------------retry -----------------------");
		return "retry";
	}

	private void handleSuccess(String key, String value, SendResult<String, String> result) {
		log.info("--------handleSuccess---------");

	}

	private void handleFailure(String key, String value, Throwable ex) throws Throwable {
		ex.printStackTrace();

		log.info("-------handleFailure---------" + ex.getStackTrace());
		// kafkaSenderI.send("Retry");
	}

	
}