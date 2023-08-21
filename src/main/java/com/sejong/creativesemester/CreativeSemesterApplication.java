package com.sejong.creativesemester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CreativeSemesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreativeSemesterApplication.class, args);
	}

}
