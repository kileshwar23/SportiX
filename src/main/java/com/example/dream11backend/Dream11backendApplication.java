package com.example.dream11backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Dream11backendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dream11backendApplication.class, args);
	}

}
