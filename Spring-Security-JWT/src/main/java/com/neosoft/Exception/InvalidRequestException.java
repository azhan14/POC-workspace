package com.neosoft.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidRequestException(String s) {
		super(s);
	}

}
