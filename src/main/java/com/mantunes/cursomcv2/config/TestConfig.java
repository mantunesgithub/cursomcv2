package com.mantunes.cursomcv2.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mantunes.cursomcv2.services.DBService;
import com.mantunes.cursomcv2.services.EmailService;
import com.mantunes.cursomcv2.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		return true;
	}
	// Qdo faz uma injeção de uma classe o spring procura um componente que pode ser 
	// @bean e traz uma instância da classe automaticamente para quamdo for executar test - será MockEmailService
	@Bean
	public	EmailService emailService() {
		return	new MockEmailService();
	}
}
