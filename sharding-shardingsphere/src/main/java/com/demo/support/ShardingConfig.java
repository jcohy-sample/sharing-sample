package com.demo.support;

import org.springframework.context.annotation.Bean;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:15:31
 * @since 2023.0.1
 */
public class ShardingConfig {

	@Bean
	public PreciseShardingByYearAlgorithm algorithm() {
		return new PreciseShardingByYearAlgorithm();
	}
}
