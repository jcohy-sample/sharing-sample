package com.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.demo.domain.Person;
import com.demo.domain.PersonProvince;
import com.demo.repository.PersonProvinceRepository;
import com.demo.repository.PersonRepository;
import org.apache.commons.lang3.RandomUtils;

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
//					new Person("jcohy01", 21, "陕西"),
//					new Person("jcohy02", 22, "四川"),
//					new Person("jcohy03", 23, "湖南"),
//					new Person("jcohy04", 24, "上海"),
//					new Person("jcohy05", 25, "北京"),
//					new Person("jcohy06", 26, "安徽"),
//					new Person("jcohy07", 27, "陕西"),
//					new Person("jcohy08", 28, "四川"),
//					new Person("jcohy09", 29, "陕西"),
//					new Person("jcohy10", 30, "湖南")
//			));
//
//			personProvinceRepository.saveAll(List.of(
//					new PersonProvince(1, "陕西", "陕西急诊记录", "备注"),
//					new PersonProvince(2, "四川", "四川急诊记录", "备注"),
//					new PersonProvince(3, "湖南", "湖南急诊记录", "备注")
//					new PersonProvince(4, "上海", "上海急诊记录", "备注"),
//					new PersonProvince(5, "北京", "北京急诊记录", "备注"),
//					new PersonProvince(6, "安徽", "安徽急诊记录", "备注"),
//					new PersonProvince(7, "陕西", "陕西急诊记录", "备注"),
//					new PersonProvince(8, "四川", "四川急诊记录", "备注"),
//					new PersonProvince(9, "陕西", "陕西急诊记录", "备注"),
//					new PersonProvince(10, "湖南", "湖南急诊记录", "备注")
//			));


			// tag::add[]
			List<String> provinces = List.of("陕西", "四川", "上海", "北京", "浙江", "安徽");

			personRepository.saveAll(IntStream.range(1, 11)
					.mapToObj(i -> {
						String province = provinces.get(i % provinces.size());
						return new Person(i, "username" + i, RandomUtils.nextInt(20, 100), province);
					})
					.collect(Collectors.toList()));

			personProvinceRepository.saveAll(IntStream.range(1, 11)
					.mapToObj(i -> {
						String province = provinces.get(i % provinces.size());
						return new PersonProvince(i, i, provinces.get(i % provinces.size()), province + "急诊记录", province);
					})
					.collect(Collectors.toList()));
			// end::add[]
		};
	}
}
