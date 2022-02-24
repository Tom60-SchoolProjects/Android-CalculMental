package com.example.calcul_mental;

import android.widget.Toast;

public class ScoreData {

    //region Variables
    private static int nbOperations;
    private static int nbTries;
    private static String lastOperation;
    //endregion

    //region Methods
    public static int getNumberOfOperations() {
        return nbOperations;
    }

    public static double getSuccessRate() {
        return (double)nbOperations * 100 / nbTries;
    }

    public static String getLastOperation() {
        return lastOperation;
    }

    public static void addOperation() {
        nbOperations++;
    }

    public static void addTries() {
        nbTries++;
    }

    public static void setLastOperation(String lastOperation) {
        ScoreData.lastOperation = lastOperation;
    }

    public static void clearData()
    {
        nbOperations = 0;
        nbTries = 0;
        lastOperation = "";
    }
    //endregion

}
