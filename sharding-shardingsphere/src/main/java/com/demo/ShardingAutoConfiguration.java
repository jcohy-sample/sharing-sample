package com.demo;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.groovy.util.Maps;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:15:39
 * @since 2023.0.1
 */
@AutoConfiguration
public class ShardingAutoConfiguration {

	private static final String STRATEGY = "strategy";

	private static final String STANDARD = "STANDARD";

	private static final String DB_SHARDING_ALGORITHM_NAME = "databaseShardingAlgorithm";

	private static final String TABLE_SHARDING_ALGORITHM_NAME = "tableShardingAlgorithm";

	private static final String CLASS_BASED = "CLASS_BASED";

	private static final String ALGORITHM_CLASS_NAME = "algorithmClassName";

	@Bean(name = "sharding")
	public DataSource dataSource() throws SQLException {

		Map<String, DataSource> dataSource = Maps.of("dataSource", createDataSource());

		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

		// 设置绑定表规则
		shardingRuleConfig.setBindingTableGroups(Collections.singleton("person_province"));

		// 生成db分片算法配置
		Properties properties = new Properties();
		properties.put("sharding-count", "6");
		shardingRuleConfig.getShardingAlgorithms()
				.put("person-province-algorithm", new AlgorithmConfiguration("HASH_MOD", properties));


		// 配置分表算法
		Properties tableShardingAlgorithmProps = new Properties();
		tableShardingAlgorithmProps.setProperty("strategy", "standard");
		tableShardingAlgorithmProps.setProperty("algorithmClassName", "person-province-algorithm");
		tableShardingAlgorithmProps.setProperty("shardingColumn", "province");

		// 配置添加表规则
		ShardingTableRuleConfiguration shardingTableRuleConfiguration = new ShardingTableRuleConfiguration("person_province", "dataSource.person_province_$->{0..5}");
		ShardingStrategyConfiguration strategyConfiguration = new StandardShardingStrategyConfiguration("province", "person-province-algorithm");
		shardingTableRuleConfiguration.setTableShardingStrategy(strategyConfiguration);
		shardingRuleConfig.getTables().add(shardingTableRuleConfiguration);


		return ShardingSphereDataSourceFactory.createDataSource(dataSource, Collections.singleton(shardingRuleConfig), new Properties());
	}

	public DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setUsername("sa");
		config.setPassword("sa");
		config.setDriverClassName("org.h2.Driver");
		config.setJdbcUrl("jdbc:h2:mem:test");
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
}
