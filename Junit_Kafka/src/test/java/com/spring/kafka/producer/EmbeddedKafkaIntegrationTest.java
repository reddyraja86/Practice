package com.spring.kafka.producer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class EmbeddedKafkaIntegrationTest {

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Value("${test.topic}")
    private String topic;

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived() 
      throws Exception {
    	ListenableFuture<SendResult<String,String>> ll =kafkaProducer.send(topic, "Sending with own simple KafkaProducer");
        kafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        System.out.println("========="+ll.get().getProducerRecord().value());
        assertThat(ll.get().getProducerRecord().value(),equalTo("Sending with own simple KafkaProducer"));
      //  assertThat(kafkaConsumer.getLatch().getCount(), equalTo(0L));
       // assertThat(kafkaConsumer.getPayload(), containsString("embedded-test-topic"));
    }
}