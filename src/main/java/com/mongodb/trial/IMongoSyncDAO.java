package com.mongodb.trial;

import java.util.List;

import org.bson.Document;

public interface IMongoSyncDAO extends IMongoDAO {
	public void insertInBulk(List<Document> document);
}
