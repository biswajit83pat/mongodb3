package com.mongodb.trial.test;

import org.testng.annotations.Test;

import com.mongodb.trial.model.Student;
import com.mongodb.trial.util.framework.PrivateAttributesAccessor;

public class PrivateMethodAccessTest {

	
	@Test
	public void testAccess() throws IllegalArgumentException, IllegalAccessException {
		
		Student st = new Student.Builder("3","Biswajit Pathak").
				rollNumber(007).
				schoolName("Stephen's").
				section("B").
				sex("Male").
				standard("II").build();
		
		PrivateAttributesAccessor.readAndSetAllPrivateFields(st);
	}
}
