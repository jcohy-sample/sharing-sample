package com.demo.sharding;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:12:11
 * @since 2023.0.1
 */
public class ShardedRepositoryFactoryBean<R extends Repository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID> {
	public ShardedRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new ShardedRepositoryFactory(entityManager);
	}
}
