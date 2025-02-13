package com.example.energias.renovables;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class EnergiasRenovablesApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(EnergiasRenovablesApplication.class, args);
	}

	@PostConstruct
	public void printSecurityConfig() {
		System.out.println("Usuario configurado: " + env.getProperty("spring.security.user.name"));
		System.out.println("Contraseña configurada: " + env.getProperty("spring.security.user.password"));
	}

}
