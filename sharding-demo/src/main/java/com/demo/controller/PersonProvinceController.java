package com.demo.controller;

import com.demo.domain.PersonProvince;
import com.demo.repository.PersonProvinceRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/10:10:39
 * @since 2023.0.1
 */
@RestController
@RequestMapping("/province")
public class PersonProvinceController {

	private final PersonProvinceRepository personProvinceRepository;

	public PersonProvinceController(PersonProvinceRepository personProvinceRepository) {
		this.personProvinceRepository = personProvinceRepository;
	}

	/**
	 * 查询
	 *
	 * @param id id
	 * @return /
	 */
	@GetMapping(value = "/{id:\\d+}")
	public PersonProvince getPersonProvince(@PathVariable("id") Integer id) {
		return personProvinceRepository.findById(id).orElseGet(PersonProvince::new);
	}


	/**
	 * 新增，修改
	 *
	 * @param personProvince personProvince
	 * @return /
	 */
	@PostMapping
	public PersonProvince savePersonProvince(@RequestBody PersonProvince personProvince) {
		return personProvinceRepository.save(personProvince);
	}


	/**
	 * 删除
	 *
	 * @param id id
	 * @return id
	 */
	@DeleteMapping(value = "/{id:\\d+}")
	public String deletePerson(@PathVariable("id") Integer id) {
		try {
			personProvinceRepository.deleteById(id);
			return "success";
		}
		catch (Exception e) {
			return "fail";
		}
	}
}
