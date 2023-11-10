package com.demo.commons.listener;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.persister.entity.EntityPersister;

import org.springframework.stereotype.Component;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:02:24
 * @since 2023.0.1
 */
@Component
public class LoadEventListenerImpl implements LoadEventListener {
	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		try {
			Class<?> aClass = Class.forName(event.getEntityClassName());
			EntityPersister persister = getPersister(event);
			EntityPersister entityPersister = persister.getEntityPersister();

			persister.load(
					event.getEntityId(),
					event.getInstanceToLoad(),
					event.getLockOptions(),
					event.getSession(),
					event.getReadOnly()
			);
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

//		String[] propertyNames = persister.getEntityMetamodel().getPropertyNames();
//		Object[] propertyValues = persister.getPropertyValues(persister.getEntityMetamodel().getClass());
//		Object[] values = persister.getPropertyValuesToInsert(entity, getMergeMap( anything ), source );


//		if (!Objects.isNull(AnnotationUtils.findAnnotation(clazz, Sharding.class))) {
//
//			String shardingField = Utils.getShardingField(clazz);
//
//			if (!StringUtils.hasText(shardingField)) {
//				throw new IllegalArgumentException("sharding field must not be null!");
//			}
//
//
//
//			Map<String, Object> map = Utils.toMap(propertyNames, propertyValues);
//			// todo 可以使用命名策略实现
//			String tableSourceName = Utils.toPhysicalTableName(clazz.getSimpleName());
//
//			ShardingStrategy shardingStrategy = SpringContextHolder.getBean(ShardingStrategy.class);
//			String tableTargetName = shardingStrategy.handler(tableSourceName, map.get(shardingField).toString());
//
//			ThreadLocalUtils.set("tableSourceName",tableSourceName);
//			ThreadLocalUtils.set("tableTargetName",tableTargetName);
//			ThreadLocalUtils.set("entity",event.getEntity());
//		}
	}

	protected EntityPersister getPersister(final LoadEvent event) {
		final Object instanceToLoad = event.getInstanceToLoad();
		if (instanceToLoad != null) {
			//the load() which takes an entity does not pass an entityName
			event.setEntityClassName(instanceToLoad.getClass().getName());
			return event.getSession().getEntityPersister(
					null,
					instanceToLoad
			);
		}
		else {
			return event.getSession().getFactory().getMetamodel().entityPersister(event.getEntityClassName());
		}
	}
}
