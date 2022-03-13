package com.example.calcul_mental.visuel;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.calcul_mental.R;
import com.example.calcul_mental.bdd.ScoreData;
import com.example.calcul_mental.bdd.ScoreModel;
import com.example.calcul_mental.logique.GenerateurDoperation;
import com.example.calcul_mental.logique.ModelDoperation;
import com.example.calcul_mental.logique.SolveurDoperation;
import com.example.calcul_mental.logique.TimeModel;
import com.example.calcul_mental.logique.exceptions.DiviseException;
import com.example.calcul_mental.logique.exceptions.OperatorException;
import com.example.calcul_mental.logique.exceptions.ResultException;
import fr.katycorp.utils.time.ClockExecutor;

import java.util.Calendar;
import java.util.Date;

import static com.example.calcul_mental.bdd.ScoreData.safeClearData;

public class JeuActivity extends AppCompatActivity {

    //region Variables
    private TextView timerText;
    private TextView operation;
    private TextView goodOperation;
    private TextView badOperation;
    private EditText operationInput;

    private double result;
    private ClockExecutor clockExecutor;
    private ActivityResultLauncher<Intent> request;

    private int totalOperation = 10;
    private ScoreModel score;
    //endregion

    //region Methods

    //region Assignation de l'interface et démarrage du jeu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        request = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    resetGame();
                    newGame();
                });

        score = new ScoreModel();

        timerText = findViewById(R.id.timer_text);
        operation = findViewById(R.id.operation_text);
        goodOperation = findViewById(R.id.good_operation_text);
        badOperation = findViewById(R.id.bad_operation_text);
        operationInput = findViewById(R.id.operation_input);

        goodOperation.setVisibility(View.INVISIBLE);
        badOperation.setVisibility(View.INVISIBLE);

        Button validateButton = findViewById(R.id.validate_operation_button);
        validateButton.setOnClickListener(view -> validateOperation());
        Button backHomeButton = findViewById(R.id.back_home_button);
        backHomeButton.setOnClickListener(view -> finish());

        newGame();

        // Start timer
        clockExecutor = new ClockExecutor((currentMilliseconds) -> runOnUiThread(() -> update(currentMilliseconds) ));
    }
    //endregion

    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_score_button)
            //ouvrir l'activité de résultat
            openActivity(ScoreActivity.class);
        else if (id == R.id.menu_reset_button)
            //nettoyer la base de donnée
            safeClearData(this);

        return super.onOptionsItemSelected(item);
    }

    private void openActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
    //endregion

    //region Logique du jeu
    private void update(long currentMilliseconds)
    {
        score.time = currentMilliseconds;
        TimeModel time = score.getTime();
        String result = getString(R.string.timer, time.getMinutes(), time.getSecondes());

        timerText.setText(result);
    }

    /**
     * Service qui génère une opération
     */
    private void newGame() {
        ModelDoperation operation = GenerateurDoperation.generate();

        try {
            result = SolveurDoperation.solve(operation);
        } catch (ResultException | DiviseException | OperatorException e) {
            e.printStackTrace();
        }

        String operationString =
                operation.getFirstValue() + " " +
                operation.getOperator() + " " +
                operation.getSecondValue();

        this.operation.setText(getString(R.string.calculate, operationString));
        ScoreData.setLastOperation(operationString + " = " + result);
        score.nbOperations++;
    }

    private void endGame() {
        Intent intent = new Intent(this, ScoreActivity.class)
                .putExtra(ScoreActivity.Action.GAME_SCORE, score);
        request.launch(intent);
    }

    private void resetGame() {
        score.nbOperations = 0;
        score.nbTries = 0;
        clockExecutor.reset();
    }

    /**
     * Service de résolution pour contenir l'opération
     */
    private void validateOperation() {
        if (operationInput.getText().toString().isEmpty())
        {
            goodOperation.setVisibility(View.GONE);
            badOperation.setVisibility(View.VISIBLE);
            badOperation.setText(R.string.empty_answer);
        }
        else
        {
            Double answer;

            ScoreData.addTries();
            score.nbTries++;

            try {
                String operation = operationInput.getText().toString();
                operation.replace(',', '.');
                answer = Double.parseDouble(operation);

                // Good answer
                if (answer == result) {
                    goodOperation.setVisibility(View.VISIBLE);
                    badOperation.setVisibility(View.GONE);
                    ScoreData.addOperation();
                    if (score.nbOperations >= totalOperation)
                        endGame();
                    else
                        newGame();
                // Bad answer
                } else {
                    goodOperation.setVisibility(View.GONE);
                    badOperation.setVisibility(View.VISIBLE);
                    badOperation.setText(R.string.bad_answer);
                }
            } catch (NumberFormatException e) {
                goodOperation.setVisibility(View.GONE);
                badOperation.setVisibility(View.VISIBLE);
                badOperation.setText(R.string.non_number_answer);
            }

            operationInput.setText("");
        }
    }
    //endregion

    //endregion
}