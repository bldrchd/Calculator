package com.sap.calcacademy.calculator.exceptions;

public class NoArgumentsException extends IllegalArgumentException {

    /**
     * Default serialization ID
     */
    private static final long serialVersionUID = 1L;

    public NoArgumentsException() {
    }

    public NoArgumentsException(String message) {
        super(message);
    }

    public NoArgumentsException(Throwable cause) {
        super(cause);
    }

    public NoArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

}
