package com.mantunes.cursomcv2.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	// Automaticamente o framework vai instaciar mailService com todos os dados
	// do Email do aplication-dev.properties
	@Autowired
	private	MailSender	mailService;
	@Autowired
	private	JavaMailSender	javaMailSender;
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Enviando email...");
		mailService.send(msg);
		LOG.info("Email enviado");
		
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando email...");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
				
	}
	
}
