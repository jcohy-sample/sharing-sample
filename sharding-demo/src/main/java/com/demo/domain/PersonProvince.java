package com.demo.domain;

import com.demo.commons.annotations.Sharding;
import com.demo.commons.annotations.ShardingField;
import javax.persistence.Column;
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
@Sharding(size = 6)
public class PersonProvince {

	@Id
	private Integer id;

	private Integer userId;

	@ShardingField
	@Column(name = "province", updatable = false)
	private String province;

	private String record;

	private String remark;

	public PersonProvince() {
	}

	public PersonProvince(Integer userId, String province, String record, String remark) {
		this(null, userId, province, record, remark);
	}

	public PersonProvince(Integer id, Integer userId, String province, String record, String remark) {
		this.id = id;
		this.userId = userId;
		this.province = province;
		this.record = record;
		this.remark = remark;
	}

	public String getProvince() {
		return province;
	}

	public PersonProvince setProvince(String name) {
		this.province = name;
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
				", name='" + province + '\'' +
				", record='" + record + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
