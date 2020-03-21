package com.bangla.snacks.common.exception;

import com.bangla.snacks.common.constants.ApplicationConstants;
import com.bangla.snacks.common.models.ErrorObject;
import com.bangla.snacks.common.util.CommonUtil;
import org.springframework.http.HttpStatus;

public class CustomerServiceException extends ApplicationError {
    private static final long serialVersionUID = -8066458944260533648L;

    private final ErrorObject errorObject;

    public CustomerServiceException(String message) {
        this(new ErrorObject(message, ApplicationConstants.ENDPOINT_NOT_AVAILABLE_ERROR_CODE,
                CommonUtil.getCurrentDateAsString(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    public int getErrorCode() {
        return 0;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    public CustomerServiceException(ErrorObject errorObject) {
        super(errorObject.getMessage());
        this.errorObject = errorObject;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }
}
