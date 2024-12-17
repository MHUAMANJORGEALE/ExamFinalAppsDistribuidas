package com.maza.denuncias_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DenunciasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DenunciasServiceApplication.class, args);
	}

}
