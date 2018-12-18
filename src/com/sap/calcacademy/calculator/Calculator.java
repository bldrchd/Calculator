package com.sap.calcacademy.calculator;

import com.sap.calcacademy.calculator.exceptions.CalculationException;
import com.sap.calcacademy.calculator.exceptions.CalculationValidationException;

public class Calculator {

    /**
     * Calculates expression from user input
     * 
     * @param inputString
     *            User input (expression) as String
     * @param validatedString
     *            the input string that passed the validation
     * @param result
     *            the calculated result from given expression
     * @return Result from the calculation
     * @throws CalculationValidationException
     *             if cannot validate the input or cannot create postfix
     *             expression
     * @throws ArithmeticException
     *             if cannot calculate the input
     */
    public Number calculate(String inputString) throws CalculationException, IllegalArgumentException {
        InputValidationAndTransformation ivat = new InputValidationAndTransformation();
        String validatedString = (ivat.validateAndTrimInput(inputString));

        CalculationAlgo ca = new CalculationAlgo();
        return Double.valueOf(ca.startCalculating(validatedString));

    }

}
