package com.mongodb.trial.util.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class PrivateAttributesAccessor {
	
	public static Document readAllPrivateMethods(Object clazz) throws IllegalArgumentException, IllegalAccessException {
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
						if (annotation == null) {
							field.setAccessible(true);
							document.append(field.getName(), field.get(clazz));
							System.out.println("Field Name --> " + field.getName() + " field value -->  " + field.get(clazz));
						} // otherwise ignore
					}
				}
			}
		}
		return document;
	}
}
