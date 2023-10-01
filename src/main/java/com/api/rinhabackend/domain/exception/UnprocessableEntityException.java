package com.api.rinhabackend.domain.exception;

public class UnprocessableEntityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnprocessableEntityException(String message) {
        super(message);
    }
}
