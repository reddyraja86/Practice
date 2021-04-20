package com.spring.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = { "javainuse-topiccc" }, partitions = 3)
@TestPropertySource(properties = { "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}" })
public class KafkaControllerIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	private Consumer<String, String> consumer;

	@Autowired
	EmbeddedKafkaBroker embeddedKafkaBroker;
	


	@BeforeEach
	void setUp() {
		Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker));
		consumer = new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new StringDeserializer()).createConsumer();
		embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
	}
	
	@AfterEach
    void tearDown() {
        consumer.close();
    }
	
	@Test
	void postAppStart() throws InterruptedException {
		ConsumerRecords<String, String> consumerRecords =KafkaTestUtils.getRecords(consumer);
		Thread.sleep(1000);
		System.out.println(consumerRecords.count()+"---------postAppStart --------"+consumerRecords.records("javainuse-topiccc"));
		assertEquals(consumerRecords.count(),2);
		
		
	//	assertEquals("Test from app start", consumerRecord.value());
		
		
	}

	@Test
	void postMessage() throws InterruptedException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());
		HttpEntity request = new HttpEntity<>(headers);

		ResponseEntity responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.GET, request,
				String.class);
		// then
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		ConsumerRecords<String, String> consumerRecords =KafkaTestUtils.getRecords(consumer);
		Thread.sleep(1000);
		System.out.println(consumerRecords.count()+"---------inside postMessage --------"+consumerRecords.records("javainuse-topic"));
		assertEquals(consumerRecords.count(),1);
	
//		Iterable<ConsumerRecord<String, String>> consumerRecordsIt = consumerRecords.records("javainuse-topic");
//		consumerRecordsIt.forEach((ConsumerRecord c)->System.out.println(c)		
//				);
		
		//ConsumerRecord<String, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, "javainuse-topic");
		//System.out.println("---------inside the --------"+consumerRecord.value());

	}
	
	
}
