package com.kaique.ifood.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.dto.request.EmailDto;
import com.kaique.ifood.exception.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

	@Autowired
	private SendGrid sendGrid;

	public void sendEmail(EmailDto dto) {

		Email from = new Email(dto.getFromEmail(), dto.getFromName());
		Email to = new Email(dto.getTo());
		Content content = new Content(dto.getContentType(), dto.getBody());
		Mail mail = new Mail(from, dto.getSubject(), to, content);

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			

			Response response = sendGrid.api(request);

			if (response.getStatusCode() >= 400 && response.getStatusCode() <= 500) 
				throw new EmailException(response.getBody());
			
		} catch (

		IOException e) {
			throw new EmailException(e.getMessage());
		}
	}
}
