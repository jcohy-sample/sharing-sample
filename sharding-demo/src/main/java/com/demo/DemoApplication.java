package com.demo;

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
//			personRepository.saveAll(List.of(
//					new Person(1, "jcohy01", 22, "陕西"),
//					new Person(2, "jcohy02", 22, "四川"),
//					new Person(3, "jcohy03", 22, "湖南"),
//					new Person(4, "jcohy04", 22, "上海"),
//					new Person(5, "jcohy05", 22, "北京")
//			));

//			personRepository.save()
		};
	}
}