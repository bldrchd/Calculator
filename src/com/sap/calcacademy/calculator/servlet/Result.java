package com.sap.calcacademy.calculator.servlet;

public class Result {
    
    private Number result = null;
    private int resultID;
    
    public Result() {
        
    }
    
    public Number getResult() {
        return result;
    }
    
    public void setResult(Number result) {
        this.result = result;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }
}
