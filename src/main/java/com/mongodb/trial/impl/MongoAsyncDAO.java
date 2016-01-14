package com.mongodb.trial.impl;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.trial.IMongoAsyncDAO;
import com.mongodb.trial.MongoDBConnector;

public class MongoAsyncDAO<T> implements IMongoAsyncDAO{

	//private MongoCollection collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection();

	private final MongoCollection<Document> collection;
	private final String collectionName;
	
	public MongoAsyncDAO(String collectionName) {
		this.collectionName = collectionName;
		collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection(collectionName);
	}
	
	public void insert(Document document) {
		collection.insertOne(document, (result, t)-> {
			Optional<Throwable> throwable = Optional.ofNullable(t);
			//throwable.ifPresent((e) -> System.out.println("error occurred while insertion, cause --> " + e.getMessage()));
			System.out.println(throwable.map(e -> "Error occurred while inserting ->" + e.getMessage()).orElse("Insertion successsful!"));
		});
	}

	public void readFirst(Document document) {
		// TODO Auto-generated method stub
		
	}

	public void readMany(Document document) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Document document) {
		// TODO Auto-generated method stub
		
	}

	public void deleteOne(Document document) {
		// TODO Auto-generated method stub
		
	}

	public void update(Document identifier, Document documentToBeUpdated) {
		// TODO Auto-generated method stub
		
	}

	public int count(Document document) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void insertBulkOrdered(List<Document> documentList) {
		
	}
	
	public void insertBulkUnordered(List<Document> documentList) {
		
	}


}
