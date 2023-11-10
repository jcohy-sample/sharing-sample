package com.demo.commons.listener;

import java.util.Map;
import java.util.Objects;

import com.demo.commons.SpringContextHolder;
import com.demo.commons.ThreadLocalUtils;
import com.demo.commons.Utils;
import com.demo.commons.annotations.Sharding;
import com.demo.commons.strategy.ShardingStrategy;
import org.hibernate.event.spi.AbstractPreDatabaseOperationEvent;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:10:04
 * @since 2023.0.1
 */
public interface ShardingEventHandler {

	Object[] getPropertyValues();

	default String getPhysicalTableName(String sourceTableName) {
		return Utils.toPhysicalTableName(sourceTableName);
	}

	default void handlerPreEvent(AbstractPreDatabaseOperationEvent event) {
		Class<?> clazz = event.getEntity().getClass();

		if (support(clazz)) {

			Integer size = getSize(clazz);
			String shardingField = Utils.getShardingField(clazz);

			// 获取原始表名
			String tableSourceName = getPhysicalTableName(clazz.getSimpleName());

			String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
			Object[] propertyValues = getPropertyValues();
			Map<String, Object> map = Utils.toMap(propertyNames, propertyValues);

			ShardingStrategy shardingStrategy = SpringContextHolder.getBean(ShardingStrategy.class);

			String tableTargetName = shardingStrategy.handler(tableSourceName, map.get(shardingField).toString(), size);

			ThreadLocalUtils.set("tableSourceName", tableSourceName);
			ThreadLocalUtils.set("tableTargetName", tableTargetName);

			ThreadLocalUtils.set("tableSourceName", tableSourceName);
			ThreadLocalUtils.set("tableTargetName", tableTargetName);
		}
	}


	default boolean support(Class<?> clazz) {
		Sharding annotation = AnnotationUtils.findAnnotation(clazz, Sharding.class);
		if (Objects.isNull(annotation)) {
			return false;
		}
		String shardingField = Utils.getShardingField(clazz);
		if (!StringUtils.hasText(shardingField)) {
			throw new IllegalArgumentException("sharding field must not be null!");
		}
		return true;
	}

	default Integer getSize(Class<?> clazz) {
		return Objects.requireNonNull(AnnotationUtils.findAnnotation(clazz, Sharding.class)).size();
	}
}
