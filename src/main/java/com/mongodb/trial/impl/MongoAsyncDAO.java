package com.mongodb.trial.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.trial.IMongoAsyncDAO;
import com.mongodb.trial.MongoDBConnector;
import com.mongodb.trial.model.Domain;
import com.mongodb.trial.util.OptionalConsumer;

public class MongoAsyncDAO<T extends Domain> extends MongoCollectionConfigBuilder implements IMongoAsyncDAO{

	//private MongoCollection collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection();

	private WriteConcern writeConcern;
	private ReadPreference readPreference;
	private final CodecRegistry codecRegistry;
	
	private final MongoCollection<Document> collection;
	private final String collectionName;
	private T obj;
	private T obj2;
	
	private MongoAsyncDAO(Builder builder) {
		super(builder);
		this.writeConcern = builder.writeConcern;
		this.readPreference = builder.readPreference;
		this.codecRegistry = builder.codecRegistry;
		this.collectionName = builder.collectionName;
		
		if(readPreference == null) {
			readPreference = ReadPreference.primaryPreferred();//by default 
		}
		
		if(writeConcern == null) {
			writeConcern = WriteConcern.SAFE;//by default
		}
		
		if(codecRegistry != null) {
			collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection(collectionName).
					withCodecRegistry(codecRegistry).
					withReadPreference(readPreference).
					withWriteConcern(writeConcern);
		} else {
			collection = MongoDBConnector.INSTANCE.getAsyncCurrentMonthDatabase().getCollection(collectionName).
					withReadPreference(readPreference).
					withWriteConcern(writeConcern);
		}
	}
	
	public static class Builder extends MongoCollectionConfigBuilder.Builder<Builder>{
		
		public Builder(String collectionName) {
			super(collectionName);
		}

		@Override		
		public MongoAsyncDAO build() {
			return new MongoAsyncDAO(this);
		}
	}
	
	public void insert(Document document) {
		collection.insertOne(document, (result, t)-> {
			OptionalConsumer.of(Optional.ofNullable(t)).
			ifPresent(s -> System.out.println("Insertion failed, cause " + s)).
			ifNotPresent(() -> System.out.println("Insertion Successful!"));
		});
	}

	public T readFirst(Document document) {
		CompletableFuture<T> result = new CompletableFuture<>();
		obj2 = null;
		collection.find().first((doc , t) -> {
			OptionalConsumer.of(Optional.ofNullable(t)).
			ifPresent(s -> System.out.println("Exception occurred in readFirst " + s.getMessage())).
			ifNotPresent(() -> {obj2 = (T) obj2.fromJson(doc); result.complete(obj2);});
		});
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;//TODO need to change
	}

	public List<T> readMany(Document document) {
		// TODO Auto-generated method stub
		return null;
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
