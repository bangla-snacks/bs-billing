package com.bangla.snacks.customer.exception;

import com.bangla.snacks.customer.constants.ApplicationConstants;
import org.springframework.http.HttpStatus;


public class NotImplementedException extends ApplicationError {

	private static final long serialVersionUID = 5445277604967202095L;
	
	private final String message;
	private final int errorCode;
	private final HttpStatus httpStatus;

	public NotImplementedException() {
		super(ApplicationConstants.NOT_IMPLEMENTED_ERROR_MESSAGE);
		this.message = super.getMessage();
		this.errorCode = ApplicationConstants.NOT_IMPLEMENTED_ERROR_CODE;
		this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
	}
	
	public int getErrorCode() {
		return this.errorCode;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
