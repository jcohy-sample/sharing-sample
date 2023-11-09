package com.demo.commons.support;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import org.springframework.stereotype.Component;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:15:15
 * @since 2023.0.1
 */
@Component
public class SaveOrUpdateEventListenerImpl implements SaveOrUpdateEventListener {
	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		System.out.println("============ SaveOrUpdateEventListenerImpl ======");
	}
}
