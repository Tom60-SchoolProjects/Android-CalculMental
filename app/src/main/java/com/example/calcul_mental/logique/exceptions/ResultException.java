package com.example.calcul_mental.logique.exceptions;

public class ResultException extends Exception {
    public ResultException(String message){
        super(message);
    }

    public ResultException(String message, Throwable innerError){
        super(message, innerError);
    }
}
