package com.demo.commons.support;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import com.demo.commons.SpringContextHolder;
import com.demo.commons.ThreadLocalUtils;
import com.demo.commons.Utils;
import com.demo.commons.annotations.Sharding;
import com.demo.commons.core.ShardingStrategy;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:10:25
 * @since 2023.0.1
 */
@Component
public class PreInsertEventListenerImp implements PreInsertEventListener {

	private static Logger logger = LoggerFactory.getLogger(PreInsertEventListenerImp.class);

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		System.out.println("onPreInsert ====");
		Class<?> clazz = event.getEntity().getClass();

		if (!Objects.isNull(AnnotationUtils.findAnnotation(clazz, Sharding.class))) {

			String shardingField = Utils.getShardingField(clazz);

			if (!StringUtils.hasText(shardingField)) {
				throw new IllegalArgumentException("sharding field must not be null!");
			}

			String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
			Object[] propertyValues = event.getState();

			Map<String, Object> map = Utils.toMap(propertyNames, propertyValues);
			// todo 可以使用命名策略实现
			String tableSourceName = Utils.toPhysicalTableName(clazz.getSimpleName());

			ShardingStrategy shardingStrategy = SpringContextHolder.getBean(ShardingStrategy.class);
			String tableTargetName = shardingStrategy.handler(tableSourceName, map.get(shardingField).toString());

			ThreadLocalUtils.set("tableSourceName",tableSourceName);
			ThreadLocalUtils.set("tableTargetName",tableTargetName);
			ThreadLocalUtils.set("entity",event.getEntity());
		}

		return false;
	}

//	public void addParams(Object entity, Map<String, Object> map) {
//		if (!Objects.isNull(AnnotationUtils.findAnnotation(entity.getClass(), Sharding.class))) {
//			// 获取表名
//			String tableSourceName = Utils.toPhysicalTableName(entity.getClass().getSimpleName());
//			params.put("tableSourceName", tableSourceName);
//
//			// 获取分页的字段
//			String shardingField = Utils.getShardingField(entity.getClass());
//
//
//			params.put("tableTargetName", tableTargetName);
//		}
//	}

}