package com.example.calcul_mental;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import fr.katycorp.utils.fragments.SimpleDialog;

import static com.example.calcul_mental.ScoreData.safeClearData;

public class JeuActivity extends AppCompatActivity {

    //region Variables
    private TextView operation;
    private TextView goodOperation;
    private TextView badOperation;
    private EditText operationInput;
    private int result;
    //endregion

    //region Methods

    //region Assignation de l'interface et démarrage du jeu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

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

    /**
     * Service qui génère une opération
     */
    private void newGame() {
        int firstNb = getRandomNumber(0, 10);
        int secondNb = getRandomNumber(0, 10);
        int operationType = getRandomNumber(0, 3);
        String operationString = "";

        switch (operationType) {
            case 0:
                operationString = "+";
                result = firstNb + secondNb;
                break;
            case 1:
                operationString = "-";
                result = firstNb - secondNb;
                break;
            case 2:
                operationString = "*";
                result = firstNb * secondNb;
                break;
            case 3:
                operationString = "/";
                if (secondNb == 0)
                    secondNb = 1;
                result = firstNb / secondNb;
                break;
        }

        String calculus = firstNb + " " + operationString + " " + secondNb;
        operation.setText(getString(R.string.calculate, calculus));
        ScoreData.setLastOperation(calculus + " = " + result);
    }

    /**
     * Service de résolution pour contenir l'opération
     */
    private void validateOperation() {
        if (operationInput.getText().toString().isEmpty())
            return;

        // TODO: Verifier que l'entré ne comporte que des chiffres
        // Good answer
        if (Integer.parseInt(operationInput.getText().toString()) == result) {
            goodOperation.setVisibility(View.VISIBLE);
            badOperation.setVisibility(View.INVISIBLE);
            ScoreData.addOperation();
            newGame();
        // Bad answer
        } else {
            goodOperation.setVisibility(View.INVISIBLE);
            badOperation.setVisibility(View.VISIBLE);
        }

        operationInput.setText("");
        ScoreData.addTries();
    }

    private static int getRandomNumber(int lowerBound, int upperBound) {
        int range = (upperBound - lowerBound) + 1;

        return (int)(Math.random() * range) + lowerBound;
    }
    //endregion

    //endregion
}