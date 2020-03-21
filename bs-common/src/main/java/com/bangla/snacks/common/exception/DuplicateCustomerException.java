package com.bangla.snacks.common.exception;

import com.bangla.snacks.common.constants.ApplicationConstants;
import com.bangla.snacks.common.util.CommonUtil;
import org.springframework.http.HttpStatus;

public class DuplicateCustomerException extends ApplicationError {

	private static final long serialVersionUID = 8028858099788854844L;

	private final String message;
	private final int errorCode;
	private final HttpStatus httpStatus;
	private final String errorTime;
	
	public DuplicateCustomerException(String userName) {
		super(String.format(ApplicationConstants.DUPLICATE_USER_ERROR_MESSAGE, userName));
		this.message = super.getMessage();
		this.errorCode = ApplicationConstants.DUPLICATE_USER_ERROR_CODE;
		this.httpStatus = HttpStatus.CONFLICT;
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
