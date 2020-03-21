package com.bangla.snacks.common.exception;

import com.bangla.snacks.common.constants.ApplicationConstants;
import com.bangla.snacks.common.util.CommonUtil;
import org.springframework.http.HttpStatus;

public class NoSuchCustomerException extends ApplicationError {

	private static final long serialVersionUID = 8028858099788854844L;

	private final String message;
	private final int errorCode;
	private final HttpStatus httpStatus;
	private final String errorTime;
	
	public NoSuchCustomerException() {
		super(ApplicationConstants.NO_SUCH_CUSTOMER_ERROR_MESSAGE);
		this.message = super.getMessage();
		this.errorCode = ApplicationConstants.NO_SUCH_USER_ERROR_CODE;
		this.httpStatus = HttpStatus.NOT_FOUND;
		this.errorTime = CommonUtil.getCurrentDateAsString();
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
