package com.demo;

import com.demo.props.ShadingTableInfo.ShardingAlgorithms;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description: 根据不同的算法生成不同的真实节点信息
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:11:57
 * @since 2023.0.1
 */
public interface ActualDataNode {

	String actualDataNode(ShardingAlgorithms shardingAlgorithms);
}
