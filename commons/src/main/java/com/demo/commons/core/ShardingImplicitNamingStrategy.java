package com.demo.commons.core;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:23:55
 * @since 2023.0.1
 */
public class ShardingImplicitNamingStrategy extends SpringImplicitNamingStrategy {

	@Override
	public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
		String owningPhysicalTableName = source.getOwningPhysicalTableName();
		String property = source.getAssociationOwningAttributePath().getProperty();
		String name = source.getOwningPhysicalTableName() + "_"
				+ source.getAssociationOwningAttributePath().getProperty();
		return toIdentifier(name, source.getBuildingContext());
	}
}
