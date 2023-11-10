package com.demo.repository;

import com.demo.domain.PersonProvince;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:54
 * @since 2023.0.1
 */
public interface PersonProvinceRepository extends JpaRepository<PersonProvince, Integer> {

	PersonProvince findByProvinceAndUserId(String province, Integer userId);

}
