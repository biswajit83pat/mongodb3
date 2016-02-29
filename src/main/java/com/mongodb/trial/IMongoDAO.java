package com.mongodb.trial;

import java.util.List;

import org.bson.Document;

import com.mongodb.trial.model.Domain;

public interface IMongoDAO<T extends Domain> {
	public void insert(Document document);
	public T readFirst(Document document);
	public List<T> readMany(Document document);
	public void delete(Document document);
	public void deleteOne(Document document);
	public void update(Document identifier, Document documentToBeUpdated);
	public int count(Document document);
}
