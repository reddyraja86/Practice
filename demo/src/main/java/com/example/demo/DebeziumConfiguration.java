package com.example.demo;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;

@Configuration
public class DebeziumConfiguration {
	
	
	@Bean
    public io.debezium.config.Configuration studentConnector() throws IOException {
				
		final Properties props = new Properties();
		props.setProperty("name", "engine");
		props.setProperty("connector.class", "io.debezium.connector.mongodb.MongoDbConnector");
		props.setProperty("mongodb.hosts", "rs0/localhost:27017");
		props.setProperty("mongodb.name", "test");
		props.setProperty("collection.include.list", "customer[.]*");

		
		props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
		props.setProperty("offset.storage.file.filename", "/path/to/storage/offset.dat");
		props.setProperty("offset.flush.interval.ms", "60000");
//		/* begin connector properties */
//		props.setProperty("database.hostname", "localhost");
//		props.setProperty("database.port", "3306");
//		props.setProperty("database.user", "mysqluser");
//		props.setProperty("database.password", "mysqlpw");
//		props.setProperty("database.server.id", "85744");
//		props.setProperty("database.server.name", "my-app-connector");
//		props.setProperty("database.history",
//		      "io.debezium.relational.history.FileDatabaseHistory");
//		props.setProperty("database.history.file.filename",
//		      "/path/to/storage/dbhistory.dat");

		// Create the engine with this configuration ...
		try (DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine.create(Json.class)
		        .using(props)
		        .notifying(record -> {
		            System.out.println("-----------------------------------"+record);
		        }).build()
		    ) {
		    // Run the engine asynchronously ...
		    ExecutorService executor = Executors.newSingleThreadExecutor();
		    executor.execute(engine);
		
		}
		
        return io.debezium.config.Configuration.from(props);
//                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
//                .with("offset.storage",  "org.apache.kafka.connect.storage.FileOffsetBackingStore")
//                .with("offset.storage.file.filename", "/tmp/student-offset.dat")
//                .with("offset.flush.interval.ms", 60000)
//                .with("name", "student-postgres-connector")
//                .with("database.server.name", studentDBHost+"-"+studentDBName)
//                .with("database.hostname", studentDBHost)
//                .with("database.port", studentDBPort)
//                .with("database.user", studentDBUserName)
//                .with("database.password", studentDBPassword)
//                .with("database.dbname", studentDBName)
//                .with("table.whitelist", STUDENT_TABLE_NAME).build();
    }

}
