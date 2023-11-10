package com.demo.commons.listener;

import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;

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
public class PreDeleteEventListenerImp implements PreDeleteEventListener, ShardingEventHandler {

	private PreDeleteEvent event;

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		System.out.println("onPreDelete ====");
		this.event = event;
		handlerPreEvent(event);
		return false;
	}

	@Override
	public Object[] getPropertyValues() {
		return event.getDeletedState();
	}
}
