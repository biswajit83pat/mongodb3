package com.mongodb.trial.test;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.trial.MongoDBConnector;

public class EyeBallTest {

	public static void main(String[] args) {
		
		//MongoDBConfigLoader mongoDBConfigLoader = new MongoDBConfigLoader();
		MongoDatabase database = MongoDBConnector.INSTANCE.getSyncCurrentMonthDatabase();
		MongoCollection<Document> collection = database.getCollection("test");
		System.out.println(collection);
		
		Document doc = new Document("name", "MongoDB")
        .append("type", "database")
        .append("count", 1)
        .append("info", new Document("x", 203).append("y", 102));
		
		System.out.println("#1 DB Async: " + MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().hashCode());
		System.out.println("#2 DB Async: " + MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().hashCode());
		
		System.out.println("#1 DB Sync: " + MongoDBConnector.INSTANCE.getSyncCurrentMonthDatabase().hashCode());
		System.out.println("#2 DB Sync: " + MongoDBConnector.INSTANCE.getSyncCurrentMonthDatabase().hashCode());
		System.out.println("#3 DB Sync: " + MongoDBConnector.INSTANCE.getSyncCurrentMonthDatabase().hashCode());
		System.out.println("#4 DB Sync: " + MongoDBConnector.INSTANCE.getSyncCurrentMonthDatabase().hashCode());
		
		System.out.println("#1 Enum Instance: " + MongoDBConnector.INSTANCE.hashCode());
		System.out.println("#1 Enum Instance: " + MongoDBConnector.INSTANCE.hashCode());
		System.out.println("#1 Enum Instance: " + MongoDBConnector.INSTANCE.hashCode());
		
		collection.insertOne(doc);
		
		System.out.println("doc --> " + doc.toJson());
		
		//Closing db connections for shutdown hook
		MongoDBConnector.INSTANCE.closeAsync();
		MongoDBConnector.INSTANCE.closeSync();
		
	}

}
