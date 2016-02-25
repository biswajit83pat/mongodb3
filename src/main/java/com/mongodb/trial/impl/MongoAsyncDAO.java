package com.mongodb.trial.impl;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.trial.IMongoAsyncDAO;
import com.mongodb.trial.MongoDBConnector;
import com.mongodb.trial.model.Domain;
import com.mongodb.trial.util.OptionalConsumer;

public class MongoAsyncDAO<T extends Domain> implements IMongoAsyncDAO{

	//private MongoCollection collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection();

	private final MongoCollection<Document> collection;
	private final String collectionName;
	
	public MongoAsyncDAO(String collectionName) {
		this.collectionName = collectionName;
		collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection(collectionName);
	}
	
	public void insert(Document document) {
		collection.insertOne(document, (result, t)-> {
			//OptionalConsumer<Throwable> error = OptionalConsumer.of(Optional.ofNullable(t)).ifPresent(s ->System.out.println("isPresent "+s)).ifNotPresent(() -> System.out.println("is not present"));
			OptionalConsumer.of(Optional.ofNullable(t)).ifPresent(s ->System.out.println("Insertion failed, cause "+s)).ifNotPresent(() -> System.out.println("Insertion Successful!"));
			//throwable.ifNotPresent()
			//throwable.ifPresent((e) -> System.out.println("error occurred while insertion, cause --> " + e.getMessage()));
			//System.out.println(throwable.map(e -> "Error occurred while inserting ->" + e.getMessage()).orElse("Insertion successsful!"));
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
