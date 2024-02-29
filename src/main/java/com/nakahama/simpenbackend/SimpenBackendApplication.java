package com.nakahama.simpenbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nakahama.simpenbackend.User.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SimpenBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpenBackendApplication.class, args);
	}

	// <-- Generate dummy for superadmin -->

	@Bean
	@Transactional
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.addDummySuperadmin();
		};
	}

}
