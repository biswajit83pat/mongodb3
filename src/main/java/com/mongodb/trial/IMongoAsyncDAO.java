package com.mongodb.trial;

import java.util.List;

import org.bson.Document;

public interface IMongoAsyncDAO extends IMongoDAO {
	public void insertBulkOrdered(List<Document> documentList);
	public void insertBulkUnordered(List<Document> documentList);
}
