package com.demo.commons;

import com.demo.commons.core.HashShardingStrategy;
import com.demo.commons.core.ShardingSQLInterceptor;
import com.demo.commons.core.ShardingStrategy;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:13
 * @since 2023.0.1
 */
@Configuration
@EnableConfigurationProperties(ShardingProperties.class)
public class JpaShardingConfiguration {

	private final ShardingProperties properties;

	public JpaShardingConfiguration(ShardingProperties properties) {
		this.properties = properties;
	}

	@Bean
	public ShardingStrategy shardingStrategy() {
		return new HashShardingStrategy(properties);
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
