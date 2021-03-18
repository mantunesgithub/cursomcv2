package com.mantunes.cursomcv2.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	// Automaticamente o framework vai instaciar mailService com todos os dados
	// do Email do aplication-dev.properties
	@Autowired
	private	MailSender	mailService;
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Enviando email...");
		mailService.send(msg);
		LOG.info("Email enviado");
		
	}
	
}
