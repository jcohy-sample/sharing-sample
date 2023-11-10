package com.demo.props;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:12:19
 * @since 2023.0.1
 */
public enum ShardingType {
	HASH("HASH_MOD"),DATA("DATA");

	ShardingType(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}
}
