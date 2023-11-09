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
public class PersonProvince extends ShardingDomain {

	@Id
	private Integer id;

	private Integer userId;

	@ShardingField
	private String name;

	private String record;

	private String remark;

	public PersonProvince() {
	}

	public PersonProvince(Integer id, Integer userId, String name, String record, String remark) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.record = record;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public PersonProvince setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public PersonProvince setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public PersonProvince setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getRecord() {
		return record;
	}

	public PersonProvince setRecord(String record) {
		this.record = record;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public PersonProvince setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	@Override
	public String toString() {
		return "PersonProvince{" +
				"id=" + id +
				", userId=" + userId +
				", name='" + name + '\'' +
				", record='" + record + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
