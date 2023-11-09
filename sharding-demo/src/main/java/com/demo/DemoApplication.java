package com.demo;

import java.util.List;

import com.demo.domain.Person;
import com.demo.domain.PersonProvince;
import com.demo.repository.PersonProvinceRepository;
import com.demo.repository.PersonRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	private final PersonRepository personRepository;

	private final PersonProvinceRepository personProvinceRepository;

	public DemoApplication(PersonRepository personRepository, PersonProvinceRepository personProvinceRepository) {
		this.personRepository = personRepository;
		this.personProvinceRepository = personProvinceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {

		// 构造数据
		return args -> {
			personRepository.saveAll(List.of(
					new Person(1, "jcohy01", 21, "陕西"),
					new Person(2, "jcohy02", 22, "四川"),
					new Person(3, "jcohy03", 23, "湖南"),
					new Person(4, "jcohy04", 24, "上海"),
					new Person(5, "jcohy05", 25, "北京"),
					new Person(6, "jcohy06", 26, "安徽"),
					new Person(7, "jcohy07", 25, "陕西"),
					new Person(8, "jcohy08", 25, "四川"),
					new Person(9, "jcohy09", 25, "陕西"),
					new Person(10, "jcohy10", 25, "湖南")
			));

			personProvinceRepository.saveAll(List.of(
					new PersonProvince(1,1,"陕西","陕西急诊记录","备注"),
					new PersonProvince(2,2,"四川","四川急诊记录","备注"),
					new PersonProvince(3,3,"湖南","湖南急诊记录","备注"),
					new PersonProvince(4,4,"上海","上海急诊记录","备注"),
					new PersonProvince(5,5,"北京","北京急诊记录","备注"),
					new PersonProvince(6,6,"安徽","安徽急诊记录","备注"),
					new PersonProvince(7,7,"陕西","安徽急诊记录","备注"),
					new PersonProvince(8,8,"四川","安徽急诊记录","备注"),
					new PersonProvince(9,9,"陕西","安徽急诊记录","备注"),
					new PersonProvince(10,10,"湖南","安徽急诊记录","备注")
			));
		};
	}
}
