package com.mongodb.trial.impl;

import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

public class MongoDBAndCollectionConfigBuilderUsage extends MongoCollectionConfigBuilder {
	
	private final WriteConcern writeConcern;
	
	private final ReadPreference readPreference;
	
	private final CodecRegistry codecRegistry;
	
	private MongoDBAndCollectionConfigBuilderUsage(Builder builder) {
		super(builder);
		this.writeConcern = builder.writeConcern;
		this.readPreference = builder.readPreference;
		this.codecRegistry = builder.codecRegistry;
	}
	
	public static class Builder extends MongoCollectionConfigBuilder.Builder<Builder>{
		
		public Builder(String collectionName) {
			super(collectionName);
		}

		@Override		
		public MongoDBAndCollectionConfigBuilderUsage build() {
			return new MongoDBAndCollectionConfigBuilderUsage(this);
		}
	}
}
