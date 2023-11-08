package com.demo.commons;

import com.demo.commons.core.ShardingSQLInterceptor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(ShardingProperties.class)
public class JpaShardingConfiguration {


	//	private final ShardingProperties properties;
//
//	public JpaShardingConfiguration(ShardingProperties properties) {
//		this.properties = properties;
//	}

	@Bean
	public HibernatePropertiesCustomizer sampleCustomizer(ShardingSQLInterceptor interceptor) {
		return ((hibernateProperties) -> hibernateProperties.put("hibernate.ejb.interceptor", interceptor.getClass().getName()));
	}

	//
	@Bean
	@ConditionalOnMissingBean
	public ShardingSQLInterceptor shardingSQLInterceptor() {
		return new ShardingSQLInterceptor();
	}

}
