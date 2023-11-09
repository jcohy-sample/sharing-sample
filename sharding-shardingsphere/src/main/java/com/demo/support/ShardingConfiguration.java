//package com.demo.support;
//
//import java.util.Map;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import javax.sql.DataSource;
//import org.apache.groovy.util.Maps;
//import org.apache.shardingsphere.sharding.rule.ShardingRule;
//import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
//import org.apache.shardingsphere.spring.boot.prop.SpringBootPropertiesConfiguration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//
//
///**
// * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
// * <p> Description:
// *
// * @author jiac
// * @version 2023.0.1 2023/11/9:15:39
// * @since 2023.0.1
// */
//@AutoConfiguration
//@AutoConfigureAfter(ShardingSphereAutoConfiguration.class)
//public class ShardingConfiguration {
//
//	@Bean(name = "sharding")
//	public DataSource dataSource() {
//		ShardingConfiguration shardingConfiguration = new ShardingConfiguration();
//
//		Map<String,DataSource> dataSourceMap = Maps.of("dataSource",createDataSource());
//
//
//	}
//
//	public DataSource createDataSource() {
//		HikariConfig config = new HikariConfig();
//		config.setUsername("sa");
//		config.setPassword("sa");
//		config.setDriverClassName("org.h2.Driver");
//		config.setJdbcUrl("jdbc:h2:mem:test");
//		DataSource dataSource = new HikariDataSource(config);
//		return dataSource;
//	}
//}
