package com.spring.kafka;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class SpringKafkaApplication {
	
	@Autowired
	KafkaSender sender;
	

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	 @Bean
	    public CommandLineRunner CommandLineRunnerBean() {
	        return (args) -> {
	            System.out.println("In CommandLineRunnerImpl ");
	            
	            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	            
	            int databaseSize =100;
	    		threadPoolTaskExecutor.setCorePoolSize(1);
	    		int i=0;
	    		Random r = new Random();
	    		
	    	//	for(i=0;i<databaseSize;i++) {
	    			System.out.println("---inside the if-----");
	    		//	executorService.schedule(new MyRunnable("MongoDB-- Key -- value--"+r.nextLong(),sender),30, TimeUnit.SECONDS);
	    			Thread t = new Thread(new MyRunnable("MongoDB-- Key -- value--"+r.nextLong(),sender));
	    			t.sleep(1000);
	    			t.start();
	    			
	    			
	    			
	    			//threadPoolTaskExecutor.execute(new MyRunnable("MongoDB-- Key -- value--"+r.nextLong(),sender));
	    			//this.wait(100);
	    		 // this.wait(2000);
	    		//}
	    		
				/*
				 * 
				 * try { for( int i=0;i<100;i++) sender.send(" My topics  raja---"+i); }
				 * catch(Exception e) {
				 * System.out.println("*************** kafka down******************");
				 * e.printStackTrace(); }
				 */	           
	        };
	    }
}
