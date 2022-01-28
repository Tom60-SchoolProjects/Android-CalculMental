package com.example.calcul_mental;

import android.text.TextUtils;
import android.view.View;
import android.widget.*;
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

        newGame();
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

        operation.setText(firstNb + " " + operationString + " " + secondNb);
    }

    private void validateOperation() {
        if (TextUtils.isEmpty(operationInput.getText().toString()))
            return;

        if (Integer.parseInt(operationInput.getText().toString()) == result) {
            goodOperation.setVisibility(View.VISIBLE);
            badOperation.setVisibility(View.INVISIBLE);
        } else {
            goodOperation.setVisibility(View.INVISIBLE);
            badOperation.setVisibility(View.VISIBLE);
        }

        operationInput.setText("");

        newGame();
    }

    private int GetRandomNumber(int lowerBound, int upperBound) {
        int range = (upperBound - lowerBound) + 1;
        int random = (int)(Math.random() * range) + lowerBound;

        return random;
    }
}