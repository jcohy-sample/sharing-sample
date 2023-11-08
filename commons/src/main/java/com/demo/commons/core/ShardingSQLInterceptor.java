package com.demo.commons.core;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.demo.commons.ShardingProperties;
import com.demo.commons.SpringContextHolder;
import com.demo.commons.annotations.ShardingField;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:14:49
 * @since 2023.0.1
 */
public class ShardingSQLInterceptor extends EmptyInterceptor {

	private String entityName;

	public ShardingSQLInterceptor() {
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("==== onLoad ==");

		return super.onLoad(entity, id, state, propertyNames, types);
	}


	@Override
	public String onPrepareStatement(String sql) {
		System.out.println("==== onPrepareStatement ==");
		ShardingProperties properties = SpringContextHolder.getBean(ShardingProperties.class);
		System.out.println("properties: " + properties);
		return sql;
	}


	@Override
	public String getEntityName(Object object) {
		System.out.println("=========== getEntityName ===========");
		if (object instanceof ShardingDomain) {

		}

		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 判断字段上是否有指定的注解
			ShardingField annotation = AnnotationUtils.findAnnotation(field, ShardingField.class);
			if (annotation != null) {
				System.out.printf("Field %s annotated\n", field.getName());
			}
		}

		ShardingField annotation = AnnotationUtils.findAnnotation(object.getClass(), ShardingField.class);

		ShardingField declaredAnnotation = object.getClass().getDeclaredAnnotation(ShardingField.class);
		System.out.println(annotation);
		if (object instanceof ShardingDomain) {

		}
		this.entityName = super.getEntityName(object);
		return super.getEntityName(object);
	}

	public ShardingSQLInterceptor setEntityName(String entityName) {
		System.out.println("=========== setEntityName ===========");
		this.entityName = entityName;
		return this;
	}
}
