package com.mongo.changeStream.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mongo.changeStream.config.MongoConfiguration;
import com.mongo.changeStream.repository.AuditRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoListener {

	@Autowired
	private MongoClient mongoClient;
	
	@Autowired
	private MongoConfiguration mongoConfiguration; 
	
	@Autowired
	private AuditRepository auditRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
	    
		System.out.println("Yaaah, I am running Mongo Listener........");
	    MongoDatabase database  = mongoClient.getDatabase(mongoConfiguration.getDatabaseName());
	    
	    /******************DO IT NOW******************
	      Check resume after event with the sample code
 	    
	      Get the change stream data
	      database.watch();
	      filter the token data
	      Save in database for audit
	      publish to kafka
	    
        //Once above is done update the code
	    
		    Get the Audit data 
		       -no audit data then database.watch(); 
		       -else get the last audit data and  database.watch().resumeAfter(resumeToken);
		    save the audit
		    publish on kafka
	    
	    *************************************************************************/
	    
	    //https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference
	    //To get the events 
	    //filter event data https://developer.mongodb.com/quickstart/java-change-streams/  
	    //https://docs.spring.io/spring-data/data-mongodb/docs/current/api/org/springframework/data/mongodb/core/messaging/ChangeStreamRequest.html
	    //https://mongodb.github.io/mongo-java-driver/3.6/driver-async/tutorials/change-streams/
	    // database.watch();
	     
	    //To resume the events we need to check this //https://docs.mongodb.com/manual/changeStreams/  -- Check this first events are resuming or not
	    //{ "_id" : ObjectId("5f4551c470e0373080fcd0b8"), "non-sql" : "mongo" }
	    //BsonDocument resumeToken = BsonDocument.parse("{_id : '1', name : 'Africa'}")
	    //database.watch().resumeAfter(resumeToken);
	     
	     //Save the audit info
	   // auditRepository.save(new Audit("sdsd", "Smisdsth"));
	   // auditRepository.save(new Audit("sdsdBob", "Smisdsdsdsth"));
	     
	     //publish on kafka
	     
	
	    
	    
	    //For resuming the old lost info when service is down
	    //Get the audit info
	    //From that get the last audit token
//	    List<Audit> auditInfoList = auditRepository.findAll();
//	    Audit lastAuditRecord = null;
//	    if(auditInfoList!=null && auditInfoList.size()>0) {
//	    	lastAuditRecord = auditInfoList.get(auditInfoList.size());
	         //call a method which will resumeStream()
	        //from this lastAuditRecord get the Id and create a BsonDocument and resume event
	        //BsonDocument resumeToken = BsonDocument.parse(lastAuditRecord.toString());
	        //database.watch().resumeAfter(resumeToken);
//	    }
	    //else {
	    	//regular Watch and get stream
	    //}
	    
	     
	    
	    
	    
	    //***********************************SAMPLE***************************************/
	    //watchCursor = db.getSiblingDB("data").sensors.watch()
		/*
		 * while (!watchCursor.isExhausted()){ if (watchCursor.hasNext()){
		 * printjson(watchCursor.next()); } }
		 */
	    
	    /*
		 * database.watch(asList(Aggregates.match(Filters.in("operationType",
		 * asList("insert", "update", "replace", "delete")))))
		 * .fullDocument(FullDocument.UPDATE_LOOKUP) .forEach(printBlock,
		 * callbackWhenFinished);
		 */
	     
	}
	
	
	
}
