package com.demo.commons.support;

import java.util.Arrays;

import com.demo.commons.core.ShardingDomain;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:10:25
 * @since 2023.0.1
 */
@Component
public class PreUpdateEventListenerImp implements PreUpdateEventListener {

	private static Logger logger = LoggerFactory.getLogger(PreUpdateEventListenerImp.class);

	@Override
	public boolean onPreUpdate(PreUpdateEvent e) {

		System.out.println("onPreUpdate====");
		if (e.getEntity() instanceof ShardingDomain) {
			String[] propertyNames = e.getPersister().getEntityMetamodel().getPropertyNames();
			Object[] oldInstance = e.getOldState();
			Object[] newInstance = e.getState();
			logger.info("PreUpdateEvent, props {} oldInstance {} newInstance {}", propertyNames, oldInstance, newInstance);

			int nameIdx = Arrays.asList(propertyNames).indexOf("name");
			Object oldVal = nameIdx > -1 ? oldInstance[nameIdx] : null;
			Object newVal = nameIdx > -1 ? newInstance[nameIdx] : null;

			boolean nameIsDirty = oldVal == null && newVal != null || oldVal != null && !oldVal.equals(newVal);

			if (nameIsDirty)
				throw new RuntimeException("Boo");
		}

		return false;
	}
}