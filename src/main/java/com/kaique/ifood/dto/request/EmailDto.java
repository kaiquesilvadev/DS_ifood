package com.kaique.ifood.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

	@NotBlank
	private String fromEmail;
	
	@NotBlank
	private String fromName;
	
	@NotBlank
	private String replyto;
	
	@NotBlank
	private String to;
	
	@NotBlank
	private String Subject;
	
	@NotBlank
	private String body;
	private String contentType;
}
