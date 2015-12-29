package com.mongodb.trial;

import org.bson.Document;

public interface IMongoDAO {
	public void insert(Document document);
	public void readFirst(Document document);
	public void readMany(Document document);
	public void delete(Document document);
	public void deleteOne(Document document);
	public void update(Document identifier, Document documentToBeUpdated);
	public int count(Document document);
}
