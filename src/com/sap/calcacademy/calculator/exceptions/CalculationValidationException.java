package com.sap.calcacademy.calculator.exceptions;

public class CalculationValidationException extends CalculationException {

    /**
     * Default serialization ID
     */
    private static final long serialVersionUID = 1L;

    public CalculationValidationException() {
        super();
    }

    public CalculationValidationException(String message) {
        super(message);
    }

    public CalculationValidationException(Throwable cause) {
        super(cause);

    }

    public CalculationValidationException(String message, Throwable cause) {
        super(message, cause);

    }

}
