package com.demo.commons;

import com.demo.commons.strategy.HashShardingStrategy;
import com.demo.commons.strategy.ShardingStrategy;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:13
 * @since 2023.0.1
 */
@AutoConfiguration
public class JpaShardingConfiguration {

	@Bean
	public ShardingStrategy shardingStrategy() {
		return new HashShardingStrategy();
	}

	@Bean
	public HibernatePropertiesCustomizer sampleCustomizer() {
		return ((hibernateProperties) -> {

			hibernateProperties.put("hibernate.ejb.interceptor", ShardingSQLInterceptor.class.getName());
//			hibernateProperties.put("hibernate.ejb.event.pre-delete", PreDeleteEventListenerImp.class.getName());
//			hibernateProperties.put("hibernate.ejb.event.pre-update", PostUpdateEventListenerImp.class.getName());
//			hibernateProperties.put("hibernate.session_factory.statement_inspector", JpaStatementInspector.class.getName());
//			hibernateProperties.put("hibernate.integrator_provider",MyIntegrator.class.getName());
//			hibernateProperties.put("hibernate.ejb.event.listener.pre-update", PreUpdateEventListenerImp.class.getName());
//			hibernateProperties.put("hibernate.ejb.event.listener.pre-delete", PreDeleteEventListenerImp.class.getName());
//			hibernateProperties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY, ShardingPhysicalNamingStrategy.class.getName());
//			hibernateProperties.put(AvailableSettings.IMPLICIT_NAMING_STRATEGY, ShardingImplicitNamingStrategy.class.getName());
		});
	}
}
