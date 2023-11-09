package com.demo.sharding;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:12:13
 * @since 2023.0.1
 */
public class ShardedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ShardedRepository<T, ID> {
	private final EntityManager entityManager;

	public ShardedRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List<T> findByShard(ID id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getDomainClass());
		Root<T> root = query.from(getDomainClass());
//		query.select(root).where(builder.lessThanOrEqualTo(root.get("id"), id)); // 根据分片策略查询
		return entityManager.createQuery(query).getResultList();
	}
}
