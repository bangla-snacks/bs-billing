package com.bangla.snacks.customer.rest.advice;

import com.bangla.snacks.customer.constants.ApplicationConstants;
import com.bangla.snacks.customer.exception.AppConstraintViolationException;
import com.bangla.snacks.customer.exception.ApplicationError;
import com.bangla.snacks.customer.util.AppResponseUtil;
import com.bangla.snacks.customer.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.bangla.snacks.customer.constants.DBConstants.ApplicationConstraints;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationAdvice.class);
    private Environment environment;
    /**
     * This exception handler is only for the services.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {
            ApplicationError.class
    })
    public ResponseEntity<Object> handleApplicationException(ApplicationError e) {
        LOG.error(String.format("ApplicationError occurred - %s", e.getLocalizedMessage()));
        String message = e.getMessage() == null ? String.format("Error occurred -[%s]", e.getClass().getSimpleName()) : e.getMessage();
        ErrorObject eo = ErrorObject.builder()
                .errorCode(e.getErrorCode())
                .message(String.format("[%s] - %s", e.getClass().getSimpleName(), message))
                .errorTime(e.getErrorTime())
                .httpStatus(e.getHttpStatus())
                .build();

        return createEntityWithStandardResponseHeaders(eo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * This exception handler is only for the services.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {AppConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(AppConstraintViolationException e) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
        String violatedConstraint = constraintViolationException.getConstraintName();
        String errorMessagePropertyName = ApplicationConstraints.byConstraintName(violatedConstraint).getMessagePropertyName();
        String message = String.format(environment.getProperty(errorMessagePropertyName), e.getParameters());

        ErrorObject eo = ErrorObject.builder()
                .errorCode(ApplicationConstants.CONSTRAINT_VIOLATION_ERROR_CODE)
                .message(message)
                .errorTime(CommonUtil.getCurrentDateAsString())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return createEntityWithStandardResponseHeaders(eo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {
            Exception.class
    })
    public ResponseEntity<Object> handleGenericException(Exception e) {
        LOG.error(String.format("Generic Exception occurred - %s", e));
        e.printStackTrace();
        ErrorObject eo = ErrorObject.builder()
                .errorCode(1010)
                .message(String.format("Error occurred with message [%s] and type [%s]", e.getMessage(), e.getClass().getSimpleName()))
                .errorTime(CommonUtil.getCurrentDateAsString())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return createEntityWithStandardResponseHeaders(eo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> createEntityWithStandardResponseHeaders(Object body, HttpStatus httpStatus) {
        return AppResponseUtil.returnResponse(body, httpStatus);
    }

}
