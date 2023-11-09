package com.demo.commons.support;

import java.util.Arrays;

import com.demo.commons.core.ShardingDomain;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
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
public class PostUpdateEventListenerImp implements PostUpdateEventListener {

	private static Logger logger = LoggerFactory.getLogger(PostUpdateEventListenerImp.class);

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		System.out.println("PostUpdateEventListener:" + event.toString());
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return false;
	}
}