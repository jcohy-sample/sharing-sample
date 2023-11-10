package com.demo.commons;

import org.hibernate.EmptyInterceptor;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:14:49
 * @since 2023.0.1
 */
public class ShardingSQLInterceptor extends EmptyInterceptor {


	public ShardingSQLInterceptor() {
	}

//	@Override
//	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		System.out.println("=========== onLoad ===========");
//		System.out.println("onLoad:" + entity.getClass().getName());
//		String shardingField = Utils.getShardingField(entity.getClass());
//		System.out.println("shardingField:" + shardingField);
//		return super.onLoad(entity, id, state, propertyNames, types);
//	}

//	@Override
//	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		// 获取字段值
//		Map<String, Object> map = Utils.toMap(propertyNames, state);
//		addParams(entity,map);
//		maps.get().putAll(map);
//		return true;
//	}


//	@Override
//	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
//		// 获取字段值
//		super.onDelete(entity, id, state, propertyNames, types);
//	}

	@Override
	public String onPrepareStatement(String sql) {
		try {
			System.out.println("=========== onPrepareStatement ===========");

			Object tableSourceName = ThreadLocalUtils.get("tableSourceName");
			Object tableTargetName = ThreadLocalUtils.get("tableTargetName");
			if (Utils.isNotNull(tableSourceName, tableTargetName)) {
				sql = sql.replaceAll(tableSourceName.toString(), tableTargetName.toString());
			}

			if (sql.toLowerCase().startsWith("select")) {

			}
			System.out.println(sql);
			return super.onPrepareStatement(sql);
		}
		finally {
			// 一定要清除 ThreadLocal 变量，避免内存泄漏
			ThreadLocalUtils.remove();
		}
	}

}
