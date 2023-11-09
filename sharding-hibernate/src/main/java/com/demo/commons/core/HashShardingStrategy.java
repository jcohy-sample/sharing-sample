package com.demo.commons.core;

import com.demo.commons.ShardingProperties;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:09
 * @since 2023.0.1
 */
public class HashShardingStrategy implements ShardingStrategy {

	private final ShardingProperties properties;

	public HashShardingStrategy(ShardingProperties properties) {
		this.properties = properties;
	}

	@Override
	public String handler(String tableSourceName,String value) {

		int i = value.hashCode() % properties.getDbs().get(tableSourceName);
		return tableSourceName + "_"+ i;
	}
}
