package com.api.rinhabackend.api.exceptionhandler;
import java.net.URI;
import java.util.Map;

import org.springframework.http.ProblemDetail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ProblemField {		
	private String name;
	private String userMessage;
}