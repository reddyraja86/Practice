package com.mongo.changeStream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration {

	@Value("${spring.mongo.uri}")
	private String mongodbURI;

	@Value("${port}")
	private String port;

	@Value("${db}")
	private String db;

	/*
	 * @Bean public MongoClient mongo() { ConnectionString connectionString = new
	 * ConnectionString("mongodb://localhost:27017/test"); MongoClientSettings
	 * mongoClientSettings =
	 * MongoClientSettings.builder().applyConnectionString(connectionString)
	 * .build();
	 * 
	 * return MongoClients.create(mongoClientSettings); }
	 */

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://" + mongodbURI + ":" + port + " ");
	}

	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), db);
	}

	public String getDatabaseName() {
		return db;
	}

}
