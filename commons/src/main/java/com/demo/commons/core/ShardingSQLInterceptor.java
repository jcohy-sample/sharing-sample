package com.demo.commons.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.commons.ShardingProperties;
import com.demo.commons.SpringContextHolder;
import com.demo.commons.Utils;
import com.demo.commons.annotations.ShardingField;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.type.Type;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:14:49
 * @since 2023.0.1
 */
public class ShardingSQLInterceptor extends EmptyInterceptor {

	private final ThreadLocal<Map<Object, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

	private final Map<String,String> caches = new HashMap<>();

	private List<String> entityNames;

	public ShardingSQLInterceptor() {
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("=========== onLoad ===========");
		System.out.println("onLoad:" + entity.getClass().getName());
		String shardingField = getShardingField(entity);
		System.out.println("shardingField:" + shardingField);
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("=========== onSave ===========");
		System.out.println("onSave:" + entity.getClass().getName());
		Map<Object, Object> params = threadLocal.get();

		Class<?> clazz = entity.getClass();

		// 判断是否需要分表
		if(entity instanceof ShardingDomain) {
			// 获取表名
			String tablePhysicalName = Utils.toPhysicalTableName(clazz.getSimpleName());
			params.put("tableName",tablePhysicalName);

			// 获取分页的字段
			String shardingField = getShardingField(entity);

			// 获取字段值
			Map<String, Object> map = Utils.toMap(propertyNames, state);
			Object o = map.get(shardingField);
			int i = o.hashCode() % 3;
			tablePhysicalName = tablePhysicalName + "_" +i;
			System.out.println("tablePhysicalName: " + tablePhysicalName);
			System.out.println("shardingField:" + shardingField);
			params.put("tablePhysicalName",tablePhysicalName);
		}

		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("=========== onDelete ===========");
		System.out.println("onDelete:" + entity.getClass().getName());
		String shardingField = getShardingField(entity);
		System.out.println("shardingField:" + shardingField);
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public String onPrepareStatement(String sql) {
		try {
			// 从 ThreadLocal 变量中获取参数，并进行处理
			Map<Object, Object> params = threadLocal.get();
//			for (Map.Entry<Object, Object> entry : params.entrySet()) {
//				Object entity = entry.getKey();
//				Object[] args = (Object[]) entry.getValue();
//				Object[] state = (Object[]) args[0];
//				String[] propertyNames = (String[]) args[1];
//				// 在此处可以对 entity 进行操作
//				// ...
//			}

			if(params.size() >0) {
				String tablePhysicalName = params.get("tablePhysicalName").toString();
				String tableName = params.get("tableName").toString();
				sql = sql.replaceAll(tableName,tablePhysicalName);
				System.out.println(sql);
				System.out.println("=========== onPrepareStatement ===========");
				threadLocal.get();
				ShardingProperties properties = SpringContextHolder.getBean(ShardingProperties.class);
			}

//		System.out.println("properties: " + properties.getDbs());
			return super.onPrepareStatement(sql);
		} finally {
			// 一定要清除 ThreadLocal 变量，避免内存泄漏
			threadLocal.remove();
		}
	}


	@Override
	public String getEntityName(Object object) {
		return super.getEntityName(object);
	}

	@Override
	public Object getEntity(String entityName, Serializable id) {
		System.out.println("=========== getEntity ===========");
		System.out.println(entityName);
		return super.getEntity(entityName, id);
	}

	@Override
	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
		System.out.println("=========== instantiate ===========");
		System.out.println(entityName);
		return super.instantiate(entityName, entityMode, id);
	}


	public String getShardingField(Object entity) {
		String fieldValue = "";
		String entityName = entity.getClass().getName();

		if(StringUtils.hasText(caches.get(entityName))) {
			fieldValue = caches.get(entityName);
		} else {
			Class<?> clazz = entity.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				// 判断字段上是否有指定的注解
				ShardingField annotation = AnnotationUtils.findAnnotation(field, ShardingField.class);
				if (annotation != null) {
					// todo 仅支持一个字段,匹配到第一个标有 ShardingField 注解的就返回
					fieldValue = field.getName();
					caches.put(entityName,field.getName());
				}
			}
		}
		return fieldValue;
	}
}
