package com.demo.commons.listener;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreLoadEventListener;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;

import org.springframework.stereotype.Component;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:13:46
 * @since 2023.0.1
 */
@Component
public class HibernateEventRegistry {

	private final PreDeleteEventListener PreDeleteEventListenerImp;

	private final PreUpdateEventListener preUpdateEventListener;

	private final PreInsertEventListener preInsertEventListener;

	private final PreLoadEventListener preLoadEventListener;

	private final LoadEventListener loadEventListener;

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	public HibernateEventRegistry(PreDeleteEventListener preDeleteEventListenerImp,
			PreUpdateEventListener preUpdateEventListener, PreInsertEventListener preInsertEventListener,
			PreLoadEventListener preLoadEventListener, LoadEventListener loadEventListener) {
		PreDeleteEventListenerImp = preDeleteEventListenerImp;
		this.preUpdateEventListener = preUpdateEventListener;
		this.preInsertEventListener = preInsertEventListener;
		this.preLoadEventListener = preLoadEventListener;
		this.loadEventListener = loadEventListener;
	}

	@PostConstruct
	protected void init() {
		EventListenerRegistry registry = entityManagerFactory.unwrap(SessionFactoryImpl.class).getServiceRegistry()
				.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListeners(preUpdateEventListener);
		registry.getEventListenerGroup(EventType.PRE_DELETE).appendListeners(PreDeleteEventListenerImp);
		registry.getEventListenerGroup(EventType.PRE_INSERT).appendListeners(preInsertEventListener);
		registry.getEventListenerGroup(EventType.PRE_LOAD).appendListeners(preLoadEventListener);
		registry.getEventListenerGroup(EventType.LOAD).appendListeners(loadEventListener);
	}

}