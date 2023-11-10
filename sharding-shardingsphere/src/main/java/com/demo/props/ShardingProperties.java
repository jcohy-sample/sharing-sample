package com.demo.props;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:11
 * @since 2023.0.1
 */
@ConfigurationProperties("sharding")
public class ShardingProperties {

	private List<ShadingTableInfo> tables;


	public List<ShadingTableInfo> getTables() {
		return tables;
	}

	public ShardingProperties setTables(List<ShadingTableInfo> tables) {
		this.tables = tables;
		return this;
	}
}
