package com.demo.props;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:11:26
 * @since 2023.0.1
 */
public class ShadingTableInfo {

	private String table;

	private String shardingColumn;

	private ShardingAlgorithms shardingAlgorithms;

	public String getTable() {
		return table;
	}

	public ShadingTableInfo setTable(String table) {
		this.table = table;
		return this;
	}

	public ShardingAlgorithms getShardingAlgorithms() {
		return shardingAlgorithms;
	}

	public ShadingTableInfo setShardingAlgorithms(ShardingAlgorithms shardingAlgorithms) {
		this.shardingAlgorithms = shardingAlgorithms;
		return this;
	}

	public String getShardingColumn() {
		return shardingColumn;
	}

	public ShadingTableInfo setShardingColumn(String shardingColumn) {
		this.shardingColumn = shardingColumn;
		return this;
	}

	public static class ShardingAlgorithms {

		private String name;

		private String shardingCount;

		private ShardingType shardingType = ShardingType.HASH;

		public String getName() {
			return name;
		}

		public ShardingAlgorithms setName(String name) {
			this.name = name;
			return this;
		}

		public String getShardingCount() {
			return shardingCount;
		}

		public ShardingAlgorithms setShardingCount(String shardingCount) {
			this.shardingCount = shardingCount;
			return this;
		}

		public ShardingType getShardingType() {
			return shardingType;
		}

		public ShardingAlgorithms setShardingType(ShardingType shardingType) {
			this.shardingType = shardingType;
			return this;
		}
	}
}
