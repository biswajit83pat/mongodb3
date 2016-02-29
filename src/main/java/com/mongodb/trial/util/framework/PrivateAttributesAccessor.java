package com.mongodb.trial.util.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.trial.model.Domain;

public class PrivateAttributesAccessor {
	
	public static Document readAndSetAllPrivateFields(Object clazz) throws IllegalArgumentException, IllegalAccessException {
		Document document = null;
		Serialize classLevel = clazz.getClass().getAnnotation(Serialize.class);
		if (classLevel != null) {
			document = new Document();
			List<String> fieldsToBeIgnored = null;
			if(!"".equals(classLevel.fieldsToIgnore())) {
				fieldsToBeIgnored = Arrays.asList(classLevel.fieldsToIgnore());
			}
			Field[] fields = clazz.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!field.isSynthetic()
						&& Modifier.isPrivate(field.getModifiers())) {
					if (!field.getName().equals("serialVersionUID") && 
							(fieldsToBeIgnored == null || 
							(fieldsToBeIgnored != null &&  !fieldsToBeIgnored.contains(field.getName())))) {
						Annotation annotation = field.getAnnotation(Ignore.class);
						field.setAccessible(true);
						if (annotation == null && field.get(clazz) != null) {
							document.append(field.getName(), field.get(clazz));
							System.out.println("Field Name --> " + field.getName() + " field value -->  " + field.get(clazz));
						} // otherwise ignore
					}
				}
			}
		}
		return document;
	}
	
	public static LinkedHashMap<String, Object> getMapFromDocument(Document document, Domain dom) throws IllegalArgumentException, IllegalAccessException {
		Serialize classLevel = dom.getClass().getAnnotation(Serialize.class);
		LinkedHashMap<String, Object> documentAsMap = null;
		if (classLevel != null) {
			document = new Document();
			Field[] fields = document.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!field.isSynthetic()
						&& Modifier.isPrivate(field.getModifiers())) {
					if (field.getName().equals("documentAsMap")) {
						documentAsMap = (LinkedHashMap<String, Object>) field.get(document);
					}
				}
			}
		}
		return documentAsMap;
	}
	
	public static Domain populateDomainObjectFromMap(Domain dom, LinkedHashMap<String, Object> map) throws IllegalArgumentException, IllegalAccessException {
		Serialize classLevel = dom.getClass().getAnnotation(Serialize.class);
		if (classLevel != null) {
			List<String> fieldsToBeIgnored = null;
			if(!"".equals(classLevel.fieldsToIgnore())) {
				fieldsToBeIgnored = Arrays.asList(classLevel.fieldsToIgnore());
			}
			Field[] fields = dom.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (!field.isSynthetic()
						&& Modifier.isPrivate(field.getModifiers())) {
					if (!field.getName().equals("serialVersionUID") && 
							(fieldsToBeIgnored == null || 
							(fieldsToBeIgnored != null &&  !fieldsToBeIgnored.contains(field.getName())))) {
						Annotation annotation = field.getAnnotation(Ignore.class);
						if (annotation == null && map.get(field.getName()) != null) {
							field.setAccessible(true);
							field.set(dom, map.get(field.getName()));
							System.out.println("Setting Field --> " + field.getName() + " field value -->  " + map.get(field.getName()));
						} // otherwise ignore
					}
				}
			}
		}
		return dom;
	}
}
