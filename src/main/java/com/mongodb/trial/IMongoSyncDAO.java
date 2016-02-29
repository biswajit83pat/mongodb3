package com.mongodb.trial;

import java.util.List;

import org.bson.Document;

import com.mongodb.trial.model.Domain;

public interface IMongoSyncDAO<T extends Domain> extends IMongoDAO {
	public void insertInBulk(List<T> document);
}
