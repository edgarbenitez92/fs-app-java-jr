package com.fullstack.springboot.fsappjavajr.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class JwtNotFoundException extends RuntimeException {

	public JwtNotFoundException(String message) {
		super(message);
	}
}