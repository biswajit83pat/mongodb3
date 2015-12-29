package com.mongodb.trial;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.SocketSettings;

public enum MongoDBConnector {
	INSTANCE;

	private com.mongodb.async.client.MongoClient mongoAsyncClient;// Async client
	private MongoClient mongoSyncClient;// Sync client
	private volatile String host;
	private volatile int port;
	
	private MongoDBConnector() {
		try {
			
			host = MongoDBConfigLoader.getMongoDBHost();
			port = MongoDBConfigLoader.getMongoPort();
			
			if (mongoSyncClient == null)
				mongoSyncClient = getSyncClient();
			if (mongoAsyncClient == null)
				mongoAsyncClient = getASyncClient();
		} catch (Exception ex) {
			System.out
					.println("Error occurred while trying to get MongoClient, cause : "
							+ ex.getMessage());
			ex.printStackTrace();
		}
	}

	private MongoClient getSyncClient() {
		Builder mongoClientOptionsBuilder = new MongoClientOptions.Builder().socketKeepAlive(true).connectTimeout(4000).socketTimeout(0);//4 secs
		MongoClientOptions mongoClientOptions = mongoClientOptionsBuilder.build();
		mongoSyncClient = new MongoClient(Arrays.asList(new ServerAddress(host, port)), mongoClientOptions);
		return mongoSyncClient;
	}
	
	private com.mongodb.async.client.MongoClient getASyncClient() {
		//return new MongoClient(MongoDBConfigLoader.getMongoDBHost(), MongoDBConfigLoader.getMongoPort());
		ClusterSettings clusterSettings = ClusterSettings.builder().hosts(Arrays.asList(new ServerAddress(host))).build();
		MongoClientSettings settings = MongoClientSettings.builder().clusterSettings(clusterSettings).socketSettings(SocketSettings.builder().keepAlive(true).connectTimeout(4, TimeUnit.SECONDS).build()).connectionPoolSettings(ConnectionPoolSettings.builder().maxConnectionLifeTime(4, TimeUnit.SECONDS).build()).build();
		mongoAsyncClient =  MongoClients.create(settings);
		return mongoAsyncClient;
	}
	
	public com.mongodb.async.client.MongoDatabase getAsyncCurrentMonthDatabase() {
		return mongoAsyncClient.getDatabase(getCurrentMonthYearDBString());
	}
	
	public MongoDatabase getSyncCurrentMonthDatabase() {
		return mongoSyncClient.getDatabase(getCurrentMonthYearDBString());
	}
	
	public com.mongodb.async.client.MongoDatabase getAsyncPreviousMonthDatabase() {
		return mongoAsyncClient.getDatabase(getPreviousMonthYearDBString());
	}
	
	public MongoDatabase getSyncPreviousMonthDatabase() {
		return mongoSyncClient.getDatabase(getPreviousMonthYearDBString());
	}
	
	private String getCurrentMonthYearDBString() {
		Calendar calendar = getCurrentCalendar();
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String year = calendar.get(Calendar.YEAR) + "";
		return month + year;
	}
	
	private String getPreviousMonthYearDBString() {
		Calendar calendar = getCurrentCalendar();
		calendar.add(Calendar.MONTH, -1);
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String year = calendar.get(Calendar.YEAR) + "";
		return month + year;
	}
	
	private static Calendar getCurrentCalendar() {
		return Calendar.getInstance();//TODO Change the logic to retrieve the Date from a remote server
	}
	
	//call it on any shutdown hook for example 
    public void closeSync(){
    	mongoSyncClient.close();
    }
    
    //call it on any shutdown hook for example 
    public void closeAsync(){
    	mongoAsyncClient.close();
    }
}
