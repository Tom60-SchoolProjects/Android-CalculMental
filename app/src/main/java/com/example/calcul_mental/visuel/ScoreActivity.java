package com.example.calcul_mental.visuel;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.calcul_mental.R;
import com.example.calcul_mental.bdd.ScoreData;
import com.example.calcul_mental.bdd.ScoreModel;
import com.example.calcul_mental.visuel.adaptaters.ScoreAdaptater;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class ScoreActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    //region Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        setResult();
        TextView nbOperationText = findViewById(R.id.nb_operations_text);
        TextView lastOperationText = findViewById(R.id.last_operation_text);
        TextView successPercentageText = findViewById(R.id.success_percentage_text);
        Button backHomeButton = findViewById(R.id.back_home_button);

        nbOperationText.setText(getString(R.string.operations_number, ScoreData.getNumberOfOperations()));
        lastOperationText.setText(getString(R.string.last_operation, ScoreData.getLastOperation()));
        successPercentageText.setText(getString(R.string.success_percentage, df.format(ScoreData.getSuccessRate())));
        backHomeButton.setOnClickListener(view -> finish());

        fillScoreList();
    }

    private void setResult() {
        TextView resultText = findViewById(R.id.result_text);
        ScoreModel gameScore = (ScoreModel) getIntent().getSerializableExtra(Action.GAME_SCORE);

        if (gameScore != null) {

            Date time = new Date(gameScore.time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);

            String result = getString(R.string.game_result,
                    gameScore.nbOperations,
                    gameScore.time / 1000 / 60,
                    calendar.get(Calendar.SECOND),
                    gameScore.nbTries);
            resultText.setText(result);
            resultText.setVisibility(View.VISIBLE);

            ScoreData.addGameResult(gameScore);
        }
        else
            resultText.setVisibility(View.GONE);
    }

    private void fillScoreList() {
        LinkedList<ScoreModel> gameResults = ScoreData.getAllGameResult();
        ListView scoreList = findViewById(R.id.score_list);
        LinearLayout scoresHeaderLayout = findViewById(R.id.scores_header_layout);
        ScoreAdaptater adapter = new ScoreAdaptater(getApplicationContext(), R.layout.adaptater_score, gameResults);

        scoreList.setAdapter(adapter);
        if (gameResults.size() == 0)
            scoresHeaderLayout.setVisibility(View.GONE);
        else
            scoresHeaderLayout.setVisibility(View.VISIBLE);
    }
    //endregion

    public static class Action {
        public static final String GAME_SCORE = "com.example.calcul_mental.score.action.game_score";
    }
}