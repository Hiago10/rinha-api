package com.api.rinhabackend.api.exceptionhandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.rinhabackend.domain.exception.UnprocessableEntityException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		
		HttpStatusCode statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;

	    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
	    
	    BindingResult bindingResult = ex.getBindingResult();
	    
	    List<ProblemField> problemFields = bindingResult.getFieldErrors().stream()
	    		.map(fieldError -> ProblemField.builder()
	    				.name(fieldError.getField())
	    				.userMessage(fieldError.getDefaultMessage())
	    				.build())
	    		.collect(Collectors.toList());
	  
		ErrorResponse errorResponse = createErrorResponseBuilder(ex, detail, problemType, statusCode)
				.property("problemFields", problemFields).build();
		
		return errorResponse;
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
    public ErrorResponse handleUnprocessableEntityException(UnprocessableEntityException ex) {
		
		HttpStatusCode statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
		ProblemType problemType = ProblemType.RECURSO_NAO_PROCESSAVEL;
		ErrorResponse errorResponse = createErrorResponseBuilder(ex, ex.getMessage(), problemType, statusCode)
				.property("userMessage", ex.getMessage()).build();
		
		return errorResponse;
	}
	
	
	private ErrorResponse.Builder createErrorResponseBuilder(Exception ex, String detail, ProblemType problemType, HttpStatusCode statusCode) {
		return ErrorResponse.builder(ex, statusCode, detail)
            .title(problemType.getTitle())
            .type(URI.create(problemType.getUri()))
            .property("timestamp", LocalDateTime.now());
	}
}
