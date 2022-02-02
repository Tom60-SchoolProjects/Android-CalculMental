package com.example.calcul_mental;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.w3c.dom.Text;

public class Jeu extends AppCompatActivity {

    TextView operation;
    TextView goodOperation;
    TextView badOperation;
    EditText operationInput;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        operation = findViewById(R.id.operation_label);
        goodOperation = findViewById(R.id.good_operation);
        badOperation = findViewById(R.id.bad_operation);
        operationInput = findViewById(R.id.operation_input);

        goodOperation.setVisibility(View.INVISIBLE);
        badOperation.setVisibility(View.INVISIBLE);

        Button validateButton = findViewById(R.id.validate_operation);
        validateButton.setOnClickListener(view -> validateOperation());
        Button backHomeButton = findViewById(R.id.back_home_button);
        backHomeButton.setOnClickListener(view -> finish());

        newGame();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void newGame() {
        int firstNb = GetRandomNumber(0, 10);
        int secondNb = GetRandomNumber(0, 10);
        int operationType = GetRandomNumber(0, 3);
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
        operation.setText(calculus);
        ScoreData.SetLastOperation(calculus + " = " + result);
    }

    private void validateOperation() {
        if (TextUtils.isEmpty(operationInput.getText().toString()))
            return;

        if (Integer.parseInt(operationInput.getText().toString()) == result) { // Good answer
            goodOperation.setVisibility(View.VISIBLE);
            badOperation.setVisibility(View.INVISIBLE);
            ScoreData.AddOperation();
            newGame();
        } else { // Bad answer
            goodOperation.setVisibility(View.INVISIBLE);
            badOperation.setVisibility(View.VISIBLE);
        }

        operationInput.setText("");
        ScoreData.AddTries();
    }

    private int GetRandomNumber(int lowerBound, int upperBound) {
        int range = (upperBound - lowerBound) + 1;
        int random = (int)(Math.random() * range) + lowerBound;

        return random;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case  R.id.submite_compute_button :
                //ouvrir l'activité de resultat
                Intent intent = new Intent(this, Score.class);
                startActivity(intent);
                break;
            case R.id.reset_button:
                //nettoyer la base de donnée
                break;
        }



        return super.onOptionsItemSelected(item);
    }
}