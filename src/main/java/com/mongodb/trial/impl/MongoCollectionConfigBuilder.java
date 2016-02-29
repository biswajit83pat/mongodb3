package com.mongodb.trial.impl;

import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

public class MongoCollectionConfigBuilder {
	
	private final WriteConcern writeConcern;
	
	private final ReadPreference readPreference;
	
	private final CodecRegistry codecRegistry;
	
	private final String collectionName;
	
	protected MongoCollectionConfigBuilder(MongoCollectionConfigBuilder.Builder<?> builder) {
		this.writeConcern = builder.writeConcern;
		this.readPreference = builder.readPreference;
		this.codecRegistry = builder.codecRegistry;
		this.collectionName = builder.collectionName;
	}
	
	public static class Builder<E extends Builder> {
		protected WriteConcern writeConcern;
		
		protected ReadPreference readPreference;
		
		protected CodecRegistry codecRegistry;
		
		protected String collectionName;
		
		public Builder(String collectionName) {
			this.collectionName = collectionName;
		}
		
		public E writeConcern(WriteConcern writeConcern) {
			this.writeConcern = writeConcern;
			return (E) this;
		}
		
		public E readPreference(ReadPreference readPreference) {
			this.readPreference = readPreference;
			return (E) this;
		}
		
		/*public E codecRegistry(CodecRegistry codecRegistry) {
			this.codecRegistry = codecRegistry;
			return (E) this;
		}*/
		
		public MongoCollectionConfigBuilder build() {
			return new MongoCollectionConfigBuilder(this);
		}
	}
}
