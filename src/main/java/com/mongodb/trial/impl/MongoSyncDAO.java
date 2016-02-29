package com.mongodb.trial.impl;

import java.util.List;

import org.bson.Document;

import com.mongodb.trial.IMongoSyncDAO;
import com.mongodb.trial.model.Domain;

public class MongoSyncDAO<T extends Domain> implements IMongoSyncDAO {

	public void insert(Document document) {
		// TODO Auto-generated method stub
		
	}

	public T readFirst(Document document) {
		// TODO Auto-generated method stub
		return null;
		
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

	@Override
	public void insertInBulk(List document) {
		// TODO Auto-generated method stub
		
	}

}
