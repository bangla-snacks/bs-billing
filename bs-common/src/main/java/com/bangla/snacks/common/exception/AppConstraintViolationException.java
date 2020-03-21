package com.bangla.snacks.customer.exception;

import org.hibernate.exception.ConstraintViolationException;

public class AppConstraintViolationException extends RuntimeException {
    private static final long serialVersionUID = -8066458944260533648L;
    private final String[] parameters;

    public AppConstraintViolationException(ConstraintViolationException e, String... parameters) {
        super(e);
        this.parameters = parameters;
    }
    public String[] getParameters() {
        return this.parameters;
    }
}
