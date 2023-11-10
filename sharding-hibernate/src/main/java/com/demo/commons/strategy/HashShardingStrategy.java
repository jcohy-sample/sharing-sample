package com.demo.commons.strategy;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:09
 * @since 2023.0.1
 */
public class HashShardingStrategy implements ShardingStrategy {

	@Override
	public String handler(String tableSourceName, String value, Integer size) {
		int i = value.hashCode() % size;
		return tableSourceName + "_" + i;
	}
}
