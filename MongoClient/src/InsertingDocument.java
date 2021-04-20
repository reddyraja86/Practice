import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertingDocument {
	public static void main( String args[] ) throws InterruptedException {
	
	// Creating a Mongo client
	MongoClient mongo = new MongoClient( "localhost" , 27017 );
	
	// Accessing the database
	MongoDatabase database = mongo.getDatabase("myDb");
	
	// Creating a collection
	database.createCollection("sampleCollection");
	System.out.println("Collection created successfully");
	
	// Retrieving a collection
	MongoCollection<Document> collection = database.getCollection("sampleCollection");
	System.out.println("Collection sampleCollection selected successfully");
	
	for(int i=0;i<50000;i++) {
		Document document = new Document("title", "MongoDB=="+i)
				.append("description", "database=="+i)
				.append("likes", i)
				.append("url", "http://www.google.com/mongodb/")
				.append("by", "RR");
				
				//Inserting document into the collection
				collection.insertOne(document);
				System.out.println("Document inserted successfully");
				
				Thread.currentThread().sleep(1000);
	}
	
}
}