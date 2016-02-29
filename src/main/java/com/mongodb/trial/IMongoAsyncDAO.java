package com.mongodb.trial;

import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

public interface IMongoAsyncDAO extends IMongoDAO {
	public void insertBulkOrdered(List<Document> documentList);
	public void insertBulkUnordered(List<Document> documentList);
}