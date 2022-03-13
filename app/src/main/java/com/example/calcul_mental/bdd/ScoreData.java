package com.example.calcul_mental.bdd;

import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.example.calcul_mental.R;
import fr.katycorp.utils.fragments.SimpleDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScoreData {

    //region Variables
    private static final ScoreModel totalScore = new ScoreModel();
    private static String lastOperation = "";
    private static final LinkedList<ScoreModel> scores = new LinkedList<>();
    //endregion

    //region Methods
    public static int getNumberOfOperations() {
        return totalScore.nbOperations;
    }

    public static Double getSuccessRate() {
        if (totalScore.nbTries == 0)
            return 0d;
        return (double)totalScore.nbOperations * 100 / totalScore.nbTries;
    }

    public static String getLastOperation() {
        return lastOperation;
    }

    public static LinkedList<ScoreModel> getAllGameResult() {
        return scores;
    }

    public static void addOperation() {
        totalScore.nbOperations++;
    }

    public static void addTries() {
        totalScore.nbTries++;
    }

    public static void addGameResult(ScoreModel game) {
        scores.add(game);
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
        totalScore.nbOperations = 0;
        totalScore.nbTries = 0;
        lastOperation = "";
    }
    //endregion

}
