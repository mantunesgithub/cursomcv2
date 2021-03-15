package com.mantunes.cursomcv2.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mantunes.cursomcv2.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

//	Controle para quando eu devo instanciar a base de dados
//	Esse value pega o valor da chave do application-dev.properties que pode ser  = create ou none ...

	@Value ("${spring.jpa.hibernate.ddl-auto}")
	private	String	strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}
}
