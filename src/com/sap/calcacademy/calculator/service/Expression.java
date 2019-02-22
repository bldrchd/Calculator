package com.sap.calcacademy.calculator.service;

public class Expression {
    
    
    private String expression;
    private Number resultID;
    
    public Expression(String expression, Number number) {
        this.setExpression(expression);
        this.setResultID(number);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Number getResultID() {
        return resultID;
    }

    public void setResultID(Number number) {
        this.resultID = number;
    }
    
}
