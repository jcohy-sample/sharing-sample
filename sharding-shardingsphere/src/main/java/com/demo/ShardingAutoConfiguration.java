package com.demo;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.demo.props.ShadingTableInfo;
import com.demo.props.ShadingTableInfo.ShardingAlgorithms;
import com.demo.props.ShardingProperties;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;


/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:15:39
 * @since 2023.0.1
 */
@AutoConfiguration
@EnableConfigurationProperties(ShardingProperties.class)
public class ShardingAutoConfiguration {

	private static final String shardingDatasource = "shardingDataSource";

	private ActualDataNode actualDataNode;

	@Bean
	@ConditionalOnMissingBean
	public ActualDataNode actualDataNode() {
		HashActualDataNode hashActualDataNode = new HashActualDataNode();
		this.actualDataNode = hashActualDataNode;
		return hashActualDataNode;
	}

	@Bean(name = shardingDatasource)
	@DependsOn("actualDataNode")
	public DataSource dataSource(ShardingProperties shardingProperties) throws SQLException {

		Map<String, DataSource> dataSource = Maps.of(shardingDatasource, createDataSource());

		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

		// 设置绑定表规则
		List<ShadingTableInfo> tables = shardingProperties.getTables();
		List<String> bindingTableGroups = tables
				.stream().map(ShadingTableInfo::getTable).collect(Collectors.toList());
		shardingRuleConfig.setBindingTableGroups(bindingTableGroups);

		// 生成db分片算法配置
		tables
				.forEach(table -> {
					ShardingAlgorithms algorithms = table.getShardingAlgorithms();
					Properties props = new Properties();
					props.put("sharding-count", algorithms.getShardingCount());
					shardingRuleConfig.getShardingAlgorithms()
							.put(algorithms.getName(), new AlgorithmConfiguration(algorithms.getShardingType().getValue(), props));
				});

		// 配置添加表规则
		tables.forEach(table -> {
			String actualDataNodes = shardingDatasource + "." + table.getTable() + actualDataNode.actualDataNode(table.getShardingAlgorithms());
			ShardingTableRuleConfiguration shardingTableRuleConfiguration = new ShardingTableRuleConfiguration(table.getTable(), actualDataNodes);
			ShardingStrategyConfiguration strategyConfiguration = new StandardShardingStrategyConfiguration(table.getShardingColumn(), table.getShardingAlgorithms().getName());
			shardingTableRuleConfiguration.setTableShardingStrategy(strategyConfiguration);
			shardingRuleConfig.getTables().add(shardingTableRuleConfiguration);
		});

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
