package com.sap.calcacademy.calculator;

import java.util.LinkedList;

import com.sap.calcacademy.calculator.exceptions.CalculationValidationException;

/**
 * Calculation of expression without generation of Postfix expression
 *
 */
public class CalculationAlgo {
    
    LinkedList<String> expression = new LinkedList<>();
    private int negativePointer = 1;

    public String startCalculating(String input) throws CalculationValidationException {

        String inputString = input;
        boolean hasParentheses = inputString.contains("(") || inputString.contains(")");
        int positionOfOpenBr = 0;
        int positionOfClosingBr = 0;
        char currentChar;
        char nextChar = 0;
        int i = 0;
        if (hasParentheses) {
            while (i < inputString.length()) {
                currentChar = inputString.charAt(i);

                if (i < inputString.length() - 1)
                    nextChar = inputString.charAt(i + 1);

                if (Character.isDigit(currentChar) && nextChar == '(') {
                    throw new CalculationValidationException("Missing operator or operand between " + i + " and " + i + 1 + "(");
                }
                if (currentChar == '(')
                    positionOfOpenBr = i;

                if (currentChar == ')') {
                    positionOfClosingBr = i;
                    inputString = inputString.substring(0, positionOfOpenBr) + startCalculating(inputString.substring(positionOfOpenBr + 1, positionOfClosingBr))
                            + inputString.substring(positionOfClosingBr + 1);
                    i = 0;
                    positionOfOpenBr = 0;
                    positionOfClosingBr = 0;
                }
                ++i;
            }

        }
        return determinateTokensForExpression(inputString);
    }

    private String determinateTokensForExpression(String inputString) {
        StringBuilder sb = new StringBuilder();

        for (int i = inputString.length() - 1; i >= 0; i--) {
            char currentChar = inputString.charAt(i);
            char prevChar = 0;

            if (i > 0) {
                prevChar = inputString.charAt(i - 1);
            }

            if (isDigit(currentChar)) {
                sb.append(currentChar);
                continue;
            }
            if (isOperator(currentChar, i, prevChar)) {
                addToExpression(sb);
                expression.add(0, String.valueOf(currentChar));
            }

        }
        addToExpression(sb);
        expression = basicExpressionCalculation(expression, "*", "/");
        expression = basicExpressionCalculation(expression, "+", "-");
        String res = expression.get(0);
        expression.removeFirst();
        return res;
    }

    private boolean isNegative(char currentChar, int i, char prevChar) {
        return (currentChar == '-' && ((i == 0) || !Character.isDigit(prevChar)));
    }

    private boolean isDigit(char currentChar) {
        return (Character.isDigit(currentChar) || (currentChar == '.'));

    }

    private boolean isOperator(char currentChar, int i, char prevChar) {
        if (currentChar == '+' || currentChar == '*' || currentChar == '/')
            return true;
        if ((currentChar == '-') && (isNegative(currentChar, i, prevChar))) {
            negativePointer = -1;
            return false;
        }
        return true;
    }

    private void addToExpression(StringBuilder sb) {
        if (sb.length() != 0) {
            sb.reverse();
            Double value = Double.valueOf(sb.toString()) * negativePointer;
            expression.add(0, value.toString());
            sb.setLength(0);
            negativePointer = 1;
        }
    }

    private LinkedList<String> basicExpressionCalculation(LinkedList<String> values, String oper1, String oper2) {
        Double result = new Double(0);
        int i = 0;
        while (i < values.size()) {
            if (values.get(i).equals(oper1) || values.get(i).equals(oper2)) {
                /*
                 * / Looks the expression if contains operator (oper1 or oper2),
                 * then calculates it
                 */
                String operator = values.get(i);
                Double operand1 = Double.valueOf(values.get(i - 1));
                Double operand2 = Double.valueOf(values.get(i + 1));

                result = findResult(operator, operand1, operand2);

                values.set(i, result.toString());
                values.remove(i + 1);
                values.remove(i - 1);
            }

            else {
                ++i;
                continue;
            }
            i = 0;
        }
        return values;
    }

    private Double findResult(String operator, Double operand1, Double operand2) {
        switch (operator) {
        case "+":
            return operand1 + operand2;
        case "-":
            return operand1 - operand2;
        case "*":
            return operand1 * operand2;
        case "/":
            return divide(operand1, operand2);
        default:
            return 0.0;
        }

    }

    private Double divide(Double operand1, Double operand2) {
        if (operand2.toString().equals("0.0"))
            throw new ArithmeticException("Can not divide by 0");
        return operand1 / operand2;
    }
}
