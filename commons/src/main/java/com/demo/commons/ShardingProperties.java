package com.demo.commons;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:11
 * @since 2023.0.1
 */
@ConfigurationProperties("db.sharding")
public class ShardingProperties {

	private int size = 1;

	private Map<String, Integer> dbs;


	public ShardingProperties() {
	}

	public int getSize() {
		return size;
	}

	public ShardingProperties setSize(int size) {
		this.size = size;
		return this;
	}

	public Map<String, Integer> getDbs() {
		return dbs;
	}

	public ShardingProperties setDbs(Map<String, Integer> dbs) {
		this.dbs = dbs;
		return this;
	}
}
