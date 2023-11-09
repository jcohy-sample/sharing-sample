//package com.demo.commons.core;
//
//import java.util.Locale;
//
//import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
//import org.hibernate.boot.model.naming.Identifier;
//import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
//
///**
// * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
// * <p> Description:
// *
// * @author jiac
// * @version 2023.0.1 2023/11/9:00:58
// * @since 2023.0.1
// */
//public class ShardingPhysicalNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {
//
//	@Override
//	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
//		return super.toPhysicalCatalogName(name, jdbcEnvironment);
//	}
//
//	@Override
//	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
//		return super.toPhysicalColumnName(name, jdbcEnvironment);
//	}
//
//	@Override
//	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
//		return super.toPhysicalSchemaName(name, jdbcEnvironment);
//	}
//
//	@Override
//	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
//		return super.toPhysicalSequenceName(name, jdbcEnvironment);
//	}
//
//	@Override
//	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
//		return super.toPhysicalTableName(name, jdbcEnvironment);
//	}
//
//
//}
