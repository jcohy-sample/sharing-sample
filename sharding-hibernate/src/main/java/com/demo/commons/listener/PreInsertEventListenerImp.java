package com.demo.commons.listener;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
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
public class PreInsertEventListenerImp implements PreInsertEventListener, ShardingEventHandler {

	private static Logger logger = LoggerFactory.getLogger(PreInsertEventListenerImp.class);

	private PreInsertEvent event;


	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		System.out.println("onPreInsert ====");
		this.event = event;

		handlerPreEvent(event);

		return false;
	}

	@Override
	public Object[] getPropertyValues() {
		return event.getState();
	}
}