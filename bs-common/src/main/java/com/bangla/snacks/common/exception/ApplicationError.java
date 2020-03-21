package com.bangla.snacks.common.exception;

import com.bangla.snacks.common.util.CommonUtil;
import org.springframework.http.HttpStatus;

public abstract class ApplicationError extends RuntimeException {
	private static final long serialVersionUID = 2340263939737135927L;

	public ApplicationError(String message) {
		super(message);
	}

	public String getMessage() {
		return super.getMessage();
	}
	public abstract int getErrorCode();
	public final String getErrorTime() {
		return CommonUtil.getCurrentDateAsString();
	}
	public abstract HttpStatus getHttpStatus();
}
