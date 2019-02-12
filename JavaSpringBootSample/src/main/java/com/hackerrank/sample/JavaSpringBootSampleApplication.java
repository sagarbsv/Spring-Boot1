package com.hackerrank.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication 
@EnableJpaRepositories(basePackages="com.hackerrank.sample.repository")
@EntityScan("com.hackerrank.sample.model")
@EnableAutoConfiguration
public class JavaSpringBootSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringBootSampleApplication.class, args);
	}

}

