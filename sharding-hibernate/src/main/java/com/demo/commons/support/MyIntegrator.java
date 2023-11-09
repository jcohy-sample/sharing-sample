package com.demo.commons.support;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:10:24
 * @since 2023.0.1
 */
public class MyIntegrator implements Integrator {

	public static final MyIntegrator INSTANCE = new MyIntegrator();

	@Override
	public void integrate(
			Metadata metadata,
			SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {

		System.out.println("======= MyIntegrator =========");
		final EventListenerRegistry eventListenerRegistry =
				serviceRegistry.getService(EventListenerRegistry.class);

		eventListenerRegistry.appendListeners(EventType.PRE_UPDATE, new PreUpdateEventListenerImp());
		eventListenerRegistry.appendListeners(EventType.SAVE, new SaveOrUpdateEventListenerImpl());
		eventListenerRegistry.appendListeners(EventType.PRE_DELETE, new PreDeleteEventListenerImp());
		eventListenerRegistry.appendListeners(EventType.POST_UPDATE, new PostUpdateEventListenerImp());
	}

	@Override
	public void disintegrate(
			SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
	}

}
