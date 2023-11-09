package com.demo.sharding;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:12:12
 * @since 2023.0.1
 */
@NoRepositoryBean
public interface ShardedRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	// 定义自定义的方法
	List<T> findByShard(ID id);
}
