package com.sap.calcacademy.calculator;

import java.util.InputMismatchException;

import com.sap.calcacademy.calculator.exceptions.CalculationValidationException;

public class InputValidationAndTransformation {

    /**
     * Performs basic validation of user input for unacceptable symbols,
     * parenthesis count, letters, removes whitespaces
     * 
     * @param inputString
     *            User input
     * @return String that is validated for parenthesis count, no whitespace,
     *         additional symbols and letters.
     * @throws InputMismatchException
     *             when letters are found or parenthesis count is not equal
     * @throws IllegalArgumentException
     *             when unacceptable symbols are found
     */
    public String validateAndTrimInput(String inputString) throws CalculationValidationException {

        inputString = removeWhitespaces(inputString);
        if (noUnacceptableSymbolsFound(inputString)) {
            if (correctParentheses(inputString))
                return inputString;
            throw new CalculationValidationException("Count of parentheses not equal.");
        }
        throw new CalculationValidationException("Found unaceptable symbols in " + inputString);
    }

    /**
     * Checks for whitespaces and removes them if there are such
     */
    public String removeWhitespaces(String inputString) {
        return inputString.replaceAll("\\s+", "");
    }

    /**
     * Counts the number of parentheses (should be equal)
     */
    public boolean correctParentheses(String joinedString) throws CalculationValidationException {
        int countLeft = 0;
        int countRight = 0;
        for (int i = 0; i <= joinedString.length() - 1; i++) {
            if (joinedString.charAt(0) == ')')
                throw new CalculationValidationException("Input starts with closing parentheses \")\"");
            if (joinedString.charAt(i) == '(') {
                countLeft++;
                if (joinedString.charAt(i + 1) == ')')
                    throw new CalculationValidationException("Empty parentheses at " + i + ". Must contains expression.");
            }
            if (joinedString.charAt(i) == ')') {
                countRight++;
            }
            if (countRight > countLeft)
                throw new CalculationValidationException("Too many closing parentheses at " + i);
        }
        return (countLeft == countRight);
    }

    /**
     * Returns TRUE if there are no symbols, that are different than + - / * ( )
     * . and digits
     */
    boolean noUnacceptableSymbolsFound(String joinedString) {
        return joinedString.matches("[0-9-\\+\\*\\/\\(\\)\\.]+");
    }
}
