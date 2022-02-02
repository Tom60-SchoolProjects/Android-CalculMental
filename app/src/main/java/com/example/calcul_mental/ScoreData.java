package com.example.calcul_mental;

public class ScoreData {
    private static int nbOperations;
    private static int nbTries;
    private static String lastOperation;

    public static int GetNumberOfOperations() {
        return nbOperations;
    }

    public static double GetSuccessRate() {
        return nbOperations * 100 / nbTries;
    }

    public static String GetLastOperation() {
        return lastOperation;
    }

    public static void AddOperation() {
        nbOperations++;
    }

    public static void AddTries() {
        nbTries++;
    }

    public static void SetLastOperation(String lastOperation) {
        ScoreData.lastOperation = lastOperation;
    }

    public static void ClearData()
    {
        nbOperations = 0;
        nbTries = 0;
        lastOperation = new String();
    }
}
