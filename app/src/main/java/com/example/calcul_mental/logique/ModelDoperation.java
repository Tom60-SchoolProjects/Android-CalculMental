package com.example.calcul_mental.logique;

public class ModelDoperation {
    private String firstValue;
    private String secondValue;
    private String operator;

    public ModelDoperation(String firstValue, String secondValue, String operator) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.operator = operator;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public String getOperator() {
        return operator;
    }
}
