package com.spring.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JunitKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitKafkaApplication.class, args);
	}

}
