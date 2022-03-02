package com.example.calcul_mental;

import android.content.Context;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.FragmentActivity;
import fr.katycorp.utils.fragments.SimpleDialog;

public class ScoreData {

    //region Variables
    private static int nbOperations = 0;
    private static int nbTries = 0;
    private static String lastOperation = "";
    //endregion

    //region Methods
    public static int getNumberOfOperations() {
        return nbOperations;
    }

    public static Double getSuccessRate() {
        if (nbTries == 0)
            return 0d;
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

    public static void safeClearData(FragmentActivity context) {
        new SimpleDialog.Builder()
                .setTitle(context.getString(R.string.clear_scores))
                .setMessage(context.getString(R.string.clear_scores_dialog))
                .setPrimaryButtonText(context.getString(R.string.yes))
                .setPrimaryButtonClick((view, f) -> {
                    ScoreData.clearData();
                    Toast
                            .makeText(context, R.string.scores_cleared, Toast.LENGTH_SHORT)
                            .show();
                })
                .setCloseButtonText(context.getString(R.string.no))
                .build()
                .show(context.getSupportFragmentManager(), "ClearScoresDialogFragment");
    }

    public static void clearData()
    {
        nbOperations = 0;
        nbTries = 0;
        lastOperation = "";
    }
    //endregion

}
