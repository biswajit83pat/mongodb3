package com.mongodb.trial.model;

import org.bson.Document;


public interface Domain {
	public Domain fromJson(Document document);
}
