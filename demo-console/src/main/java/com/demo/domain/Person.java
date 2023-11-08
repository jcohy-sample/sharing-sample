package com.demo.domain;

import com.demo.commons.annotations.ShardingField;
import com.demo.commons.core.ShardingDomain;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/8:15:48
 * @since 2023.0.1
 */
@Entity
public class Person extends ShardingDomain {

	@Id
	private Integer id;

	private String name;

	private Integer age;

	@ShardingField
	private String province;

	public Person() {
	}

	public Person(Integer id, String name, Integer age, String province) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.province = province;
	}


	public Integer getId() {
		return id;
	}

	public Person setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public Person setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getProvince() {
		return province;
	}

	public Person setProvince(String province) {
		this.province = province;
		return this;
	}
}
