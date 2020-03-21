package com.bangla.snacks.customer.json.models;

import java.io.Serializable;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
@Builder
public class ErrorObject implements Serializable {

	private static final long serialVersionUID = 6596361310526524560L;

	public ErrorObject() {

	}

	public ErrorObject(String message, int errorCode, String errorTime, HttpStatus httpStatus) {
		this.message = message;
		this.errorCode = errorCode;
		this.errorTime = errorTime;
		this.httpStatus = httpStatus;
	}

	private String message;
	private int errorCode;
	private String errorTime;
	private HttpStatus httpStatus;

}
