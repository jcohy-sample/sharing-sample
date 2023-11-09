package com.demo.commons.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import com.demo.commons.ShardingProperties;
import com.demo.commons.SpringContextHolder;
import com.demo.commons.ThreadLocalUtils;
import com.demo.commons.Utils;
import com.demo.commons.annotations.Sharding;
import com.demo.commons.annotations.ShardingField;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.type.Type;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.util.ObjectUtils;
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

//	private final ThreadLocal<Map<String, String>> threadLocal = ThreadLocal.withInitial(HashMap::new);
//
//	private final ThreadLocal<Map<String, Object>> maps = ThreadLocal.withInitial(HashMap::new);
//
//	private final Map<String, String> caches = new HashMap<>();

	public ShardingSQLInterceptor() {
	}

//	@Override
//	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		System.out.println("=========== onLoad ===========");
//		System.out.println("onLoad:" + entity.getClass().getName());
//		String shardingField = Utils.getShardingField(entity.getClass());
//		System.out.println("shardingField:" + shardingField);
//		return super.onLoad(entity, id, state, propertyNames, types);
//	}

//	@Override
//	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		// 获取字段值
//		Map<String, Object> map = Utils.toMap(propertyNames, state);
//		addParams(entity,map);
//		maps.get().putAll(map);
//		return true;
//	}


//	@Override
//	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		// 获取字段值
//		super.onDelete(entity, id, state, propertyNames, types);
//	}


//	public void addParams(Object entity,Map<String, Object> map) {
//		Map<String, String> params = threadLocal.get();
//		if (!Objects.isNull(AnnotationUtils.findAnnotation(entity.getClass(), Sharding.class))) {
//			// 获取表名
//			String tableSourceName = Utils.toPhysicalTableName(entity.getClass().getSimpleName());
//			params.put("tableSourceName", tableSourceName);
//
//			// 获取分页的字段
//			String shardingField = Utils.getShardingField(entity.getClass());
//
//			ShardingStrategy shardingStrategy = SpringContextHolder.getBean(ShardingStrategy.class);
//			String tableTargetName = shardingStrategy.handler(tableSourceName, map.get(shardingField).toString());
//
//			params.put("tableTargetName", tableTargetName);
//		}
//	}
//

	@Override
	public String onPrepareStatement(String sql) {
		try {
			System.out.println("=========== onPrepareStatement ===========");

			Object tableSourceName = ThreadLocalUtils.get("tableSourceName");
			Object tableTargetName = ThreadLocalUtils.get("tableTargetName");
			if(Utils.isNotNull(tableSourceName,tableTargetName)) {
				sql = sql.replaceAll(tableSourceName.toString(),tableTargetName.toString());
			}

			System.out.println(sql);
			return super.onPrepareStatement(sql);
		}
		finally {
			// 一定要清除 ThreadLocal 变量，避免内存泄漏
			ThreadLocalUtils.remove();
		}
	}

}
