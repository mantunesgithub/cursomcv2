package com.mantunes.cursomcv2.services;


import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.mantunes.cursomcv2.domain.Pedido;

public interface EmailService {

	//Texto plano 
	void	sendOrderConfirmationEmail(Pedido obj);
	
	void	sendEmail(SimpleMailMessage msg);
	
	//Texto em HTML 
	void	sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void 	sendHtmlEmail(MimeMessage mm);
}
