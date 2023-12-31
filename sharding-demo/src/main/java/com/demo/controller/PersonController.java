package com.demo.controller;

import com.demo.domain.Person;
import com.demo.domain.PersonProvince;
import com.demo.repository.PersonProvinceRepository;
import com.demo.repository.PersonRepository;

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
 * @version 2023.0.1 2023/11/8:23:09
 * @since 2023.0.1
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonRepository personRepository;

	private final PersonProvinceRepository personProvinceRepository;

	public PersonController(PersonRepository personRepository, PersonProvinceRepository personProvinceRepository) {
		this.personRepository = personRepository;
		this.personProvinceRepository = personProvinceRepository;
	}


	/**
	 * 查询
	 *
	 * @param id id
	 * @return /
	 */
	@GetMapping(value = "/{id:\\d+}")
	public Person getPerson(@PathVariable("id") Integer id) {
		return personRepository.findById(id).orElseGet(Person::new);
	}

	/**
	 * 查询，联表查询
	 *
	 * @param id id
	 * @return /
	 */
	@GetMapping(value = "/province/{id}")
	public PersonProvince getPersonByProvince(@PathVariable("id") Integer id) {
		return personRepository.findById(id)
				.map(person -> personProvinceRepository.findByProvinceAndUserId(person.getProvince(), person.getId()))
				.orElseGet(PersonProvince::new);
	}

	/**
	 * 新增，修改
	 *
	 * @param person person
	 * @return /
	 */
	@PostMapping
	public Person savePerson(@RequestBody Person person) {
		return personRepository.save(person);
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
			personRepository.deleteById(id);
			return "success";
		}
		catch (Exception e) {
			return "fail";
		}
	}
}
