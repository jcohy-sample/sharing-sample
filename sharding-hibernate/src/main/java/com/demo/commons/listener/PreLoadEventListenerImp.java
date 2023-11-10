package com.demo.commons.listener;

import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;

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
public class PreLoadEventListenerImp implements PreLoadEventListener, ShardingEventHandler {

	private PreLoadEvent event;

	@Override
	public void onPreLoad(PreLoadEvent event) {
		System.out.println("onPreLoad ====");
		this.event = event;
	}

	@Override
	public Object[] getPropertyValues() {
		return null;
	}
}
