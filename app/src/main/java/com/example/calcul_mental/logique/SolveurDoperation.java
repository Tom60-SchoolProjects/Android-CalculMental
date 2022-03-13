package com.example.calcul_mental.logique;

import com.example.calcul_mental.logique.exceptions.DiviseException;
import com.example.calcul_mental.logique.exceptions.OperatorException;
import com.example.calcul_mental.logique.exceptions.ResultException;

public class SolveurDoperation {
    /**
     * Résout une opération
     */
    public static double solve(ModelDoperation operation)
            throws ResultException, DiviseException, OperatorException {
        String firstValueAsString = operation.getFirstValue();
        String secondValueAsString = operation.getSecondValue();
        String operator = operation.getOperator();

        double firstValue;
        double secondValue;

        try {
            firstValue = Double.parseDouble(firstValueAsString);
            secondValue = Double.parseDouble(secondValueAsString);
        } catch(NumberFormatException ex){
            throw new ResultException("values are not numbers", ex);
        }

        double result;
        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if(secondValue == 0) {
                    throw new DiviseException("divise by 0");
                }

                result = firstValue / secondValue;
                break;
            default:
                throw new OperatorException("operator invalid");
        }

        return result;
    }
}
