package com.demo.commons.core;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description: 分片策略
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:09
 * @since 2023.0.1
 */
public interface ShardingStrategy {

	String handler(String tableSourceName,String value);

}
