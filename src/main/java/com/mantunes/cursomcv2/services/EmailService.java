package com.mantunes.cursomcv2.services;


import org.springframework.mail.SimpleMailMessage;

import com.mantunes.cursomcv2.domain.Pedido;

public interface EmailService {

	void	sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
