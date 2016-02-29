package com.mongodb.trial.model;

import java.io.Serializable;

import org.bson.Document;

import com.mongodb.trial.util.framework.Ignore;
import com.mongodb.trial.util.framework.PrivateAttributesAccessor;
import com.mongodb.trial.util.framework.Serialize;

@Serialize(fieldsToIgnore={"schoolName","rollNumber","blah blah"})
public class Student implements Serializable, Domain{
	
	private static final long serialVersionUID = 1L;
	
	volatile Document document;

	private String _id;
	private int rollNumber;
	@Ignore
	private String name;
	private String sex;
	private String schoolName;
	private String standard;
	private String section;
	
	private Student() {
		//for internal use
	}
	
	private Student(Builder builder) {
		this._id = builder.builder_id;
		this.rollNumber = builder.builderRollNumber;
		this.name = builder.builderName;
		this.sex = builder.builderSex;
		this.schoolName = builder.builderSchoolName;
		this.standard = builder.builderStandard;
		this.section = builder.builderSection;
		
		this.document();//Call to initialize the document
	}
	
	public int getRollNumber() {
		return rollNumber;
	}

	public String getName() {
		return name;
	}

	public String get_id() {
		return _id;
	}

	public String getSex() {
		return sex;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public String getStandard() {
		return standard;
	}

	public String getSection() {
		return section;
	}

	public static class Builder {
		private String builder_id;
		private int builderRollNumber;
		private String builderName;
		private String builderSex;
		private String builderSchoolName;
		private String builderStandard;
		private String builderSection;
		
		public Builder(String _id, String name){
			this.builder_id = _id;
			this.builderName = name;			
		}
		
		public Builder rollNumber(int rollNum) {
			this.builderRollNumber = rollNum;
			return this;
		}

		public Builder sex(String sex) {
			this.builderSex = sex;
			return this;
		}
		
		public Builder schoolName(String schoolName) {
			this.builderSchoolName = schoolName;
			return this;
		}
		
		public Builder standard(String standard) {
			this.builderStandard = standard;
			return this;
		}
		
		public Builder section(String section) {
			this.builderSection = section;
			return this;
		}
		
		public Student build() {
			return new Student(this);
		}
	}
	
	public synchronized String toJson() {
		return document != null ? document.toJson() : null;
	}
	
	public Document document() {
		if(document == null) {
			synchronized(this) {
				if(document == null) {
					try {
						document = PrivateAttributesAccessor.readAndSetAllPrivateFields(this);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
						//TODO do logging
					}
				}
			}
		}
		return document;
	}

	@Override
	public Student fromJson(Document document) {
		Student student = new Student();
		try {
			student = (Student) PrivateAttributesAccessor.populateDomainObjectFromMap(student, PrivateAttributesAccessor.getMapFromDocument(document, student));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			//TODO do logging
		}
		return student;
	}
	
}
