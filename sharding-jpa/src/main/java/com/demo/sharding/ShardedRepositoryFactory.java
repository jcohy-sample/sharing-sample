package com.demo.sharding;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:12:11
 * @since 2023.0.1
 */
public class ShardedRepositoryFactory extends JpaRepositoryFactory {
	public ShardedRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
		Class<?> repositoryInterface = information.getRepositoryInterface();
		if (ShardedRepository.class.isAssignableFrom(repositoryInterface)) {
			return new ShardedRepositoryImpl<>(information.getDomainType(), entityManager);
		}
		else {
			return super.getTargetRepository(information, entityManager);
		}
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		if (ShardedRepository.class.isAssignableFrom(metadata.getRepositoryInterface())) {
			return ShardedRepositoryImpl.class;
		}
		else {
			return super.getRepositoryBaseClass(metadata);
		}
	}
}
