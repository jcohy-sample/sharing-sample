package com.demo.commons;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:20:00
 * @since 2023.0.1
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean, Ordered {

	private static ApplicationContext applicationContext;

	public static <T> T getBean(Class<T> requiredType) {
		if (applicationContext == null) {
			throw new IllegalStateException("ApplicationContext 属性未注入");
		}
		return (T) applicationContext.getBean(requiredType);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringContextHolder.applicationContext = context;
	}

	@Override
	public void destroy() throws Exception {
		applicationContext = null;
	}

	@Override
	public int getOrder() {
		return -1;
	}

}
