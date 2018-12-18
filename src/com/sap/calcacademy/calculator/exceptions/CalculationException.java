package com.sap.calcacademy.calculator.exceptions;

public class CalculationException extends RuntimeException {
    /**
     * Default serialization ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The default constructor of CalculationException
     */
    public CalculationException() {
        super();
    }

    /**
     * Constructs exception with message
     * 
     * @param message
     *            as string that describes the exception
     */
    public CalculationException(String message) {
        super(message);
    }

    /**
     * Constructs exception with cause
     * 
     * @param cause
     *            of the exception
     */
    public CalculationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs exception with message and cause
     * 
     * @param message
     *            as string that describes the exception
     * @param cause
     *            of the exception
     */
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
