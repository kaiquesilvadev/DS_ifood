package com.kaique.ifood.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.request.EmailDto;
import com.kaique.ifood.services.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emails")
public class EmailControlle {

	@Autowired
	private EmailService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sendEmail(@Valid @RequestBody EmailDto dto) {
		service.sendEmail(dto);
	}

}
