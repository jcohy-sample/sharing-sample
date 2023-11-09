package com.demo.commons;

import org.hibernate.resource.jdbc.spi.StatementInspector;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:14:32
 * @since 2023.0.1
 */
public class JpaStatementInspector implements StatementInspector {

	@Override
	public String inspect(String sql) {

		System.out.println("===== JpaStatementInspector =====");
		System.out.println(sql);
		return null;
	}
}
