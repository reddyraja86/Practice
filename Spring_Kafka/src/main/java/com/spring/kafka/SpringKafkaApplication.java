package com.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringKafkaApplication {
	
	@Autowired
	KafkaSender sender;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	 @Bean
	    public CommandLineRunner CommandLineRunnerBean() {
	        return (args) -> {
	            System.out.println("In CommandLineRunnerImpl ");

	            try {
	            	for( int i=0;i<100;i++)
	            	sender.send(" My topics  raja---"+i);
	            }
	            catch(Exception e) {
	            	System.out.println("*************** kafka down******************");
	            	e.printStackTrace();
	            }
	           
	        };
	    }
}
