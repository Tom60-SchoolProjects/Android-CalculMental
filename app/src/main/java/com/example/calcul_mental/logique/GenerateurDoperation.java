package com.example.calcul_mental.logique;

public class GenerateurDoperation {
    /**
     * Génère une opération
     */
    public static ModelDoperation generate() {
        int firstNb = getRandomNumber(0, 10);
        int secondNb = getRandomNumber(0, 10);
        int operationType = getRandomNumber(0, 3);
        String operationString = "";

        switch (operationType) {
            case 0:
                operationString = "+";
                break;
            case 1:
                operationString = "-";
                break;
            case 2:
                operationString = "*";
                break;
            case 3:
                operationString = "/";
                if (secondNb == 0)
                    secondNb = 1;
                break;
        }

        return new ModelDoperation(
                Integer.toString(firstNb),
                Integer.toString(secondNb),
                operationString);
    }

    private static int getRandomNumber(int lowerBound, int upperBound) {
        int range = (upperBound - lowerBound) + 1;

        return (int)(Math.random() * range) + lowerBound;
    }
}
