package com.demo.support;

import java.util.Collection;
import java.util.Properties;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:15:46
 * @since 2023.0.1
 */
public class PreciseShardingByYearAlgorithm  implements StandardShardingAlgorithm<String> {

	public PreciseShardingByYearAlgorithm() {
	}

	@Override
	public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
		Comparable value = shardingValue.getValue();
		return null;
	}

	@Override
	public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
		return null;
	}

	@Override
	public Properties getProps() {
		return null;
	}

	@Override
	public void init(Properties props) {

	}
}
