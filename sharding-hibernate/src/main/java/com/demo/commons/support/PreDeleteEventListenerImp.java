package com.demo.commons.support;

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
public class PreDeleteEventListenerImp implements PreDeleteEventListener {

	@Override
	public boolean onPreDelete(PreDeleteEvent e) {
		System.out.println("onPreDelete ====");
		throw new RuntimeException("Cannot delete");
	}
}
