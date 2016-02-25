package com.mongodb.trial.test;

import org.bson.Document;
import org.testng.annotations.Test;

import com.mongodb.trial.IMongoDAO;
import com.mongodb.trial.impl.MongoAsyncDAO;
import com.mongodb.trial.model.Student;

public class TestCRUDSync {

	
	@Test
	public void testInsert() {
		IMongoDAO mongoDAO = new MongoAsyncDAO("Example");
		Document document = new Document();
		document.append("key1", "value1").append("key2", "value2").append("key3", "value3").append("_id","2");
		//mongoDAO.insert(document);
		
		
		
		
		Student student = new Student.Builder("4","Biswajit Pathak").
				rollNumber(007).
				schoolName("Stephen's").
				section("B").
				sex("Male").
				standard("II").build();
		
		System.out.println(student.toJson());
		
		mongoDAO.insert(student.document());
	}
}
