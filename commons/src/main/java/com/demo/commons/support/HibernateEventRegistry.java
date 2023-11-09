package com.demo.commons.support;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private PreDeleteEventListener PreDeleteEventListenerImp;

	@Autowired
	private PreUpdateEventListener preUpdateEventListener;


	@Autowired
	private SaveOrUpdateEventListener saveOrUpdateEventListener;

	@PostConstruct
	protected void init(){
		EventListenerRegistry registry = entityManagerFactory.unwrap(SessionFactoryImpl.class).getServiceRegistry()
				.getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListeners(preUpdateEventListener);
		registry.getEventListenerGroup(EventType.PRE_DELETE).appendListeners(PreDeleteEventListenerImp);
		registry.getEventListenerGroup(EventType.SAVE_UPDATE).appendListeners(saveOrUpdateEventListener);
	}

}