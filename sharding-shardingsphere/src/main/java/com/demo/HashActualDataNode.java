package com.demo;

import com.demo.props.ShadingTableInfo.ShardingAlgorithms;
import com.demo.props.ShardingType;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:12:00
 * @since 2023.0.1
 */
public class HashActualDataNode implements ActualDataNode {

	@Override
	public String actualDataNode(ShardingAlgorithms shardingAlgorithms) {
		if(shardingAlgorithms.getShardingType().equals(ShardingType.HASH)) {
			int count = Integer.parseInt(shardingAlgorithms.getShardingCount()) - 1;
			return String.format("_$->{0..%s}",count);
		}
		return "";
	}
}
