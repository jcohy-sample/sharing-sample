package com.demo.controller;

import com.demo.commons.annotations.ShardingField;
import com.demo.domain.Person;
import com.demo.repository.PersonProvinceRepository;
import com.demo.repository.PersonRepository;

import org.springframework.core.annotation.AnnotationUtils;
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


	@GetMapping(value = "/{id:\\d+}")
	public Person getPerson(@PathVariable("id") Integer id) {
		personRepository.findById(id)
				.ifPresent(person -> {
					System.out.println(person);
					personProvinceRepository.findByName(person.getProvince());
				});
		return personRepository.findById(id).get();
	}

	@PostMapping
	public Person savePerson(@RequestBody Person person) {
		ShardingField annotation = AnnotationUtils.findAnnotation(person.getClass(), ShardingField.class);
		return personRepository.save(person);
	}


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
