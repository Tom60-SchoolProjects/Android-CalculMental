package com.example.calcul_mental;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.DecimalFormat;

public class ScoreActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    //region Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView nbOperationText = findViewById(R.id.nb_operations_text);
        TextView lastOperationText = findViewById(R.id.last_operation_text);
        TextView successPercentageText = findViewById(R.id.success_percentage_text);
        Button backHomeButton = findViewById(R.id.back_home_button);

        nbOperationText.setText(getString(R.string.operations_number, ScoreData.getNumberOfOperations()));
        lastOperationText.setText(getString(R.string.last_operation, ScoreData.getLastOperation()));
        successPercentageText.setText(getString(R.string.success_percentage, df.format(ScoreData.getSuccessRate())));
        backHomeButton.setOnClickListener(view -> finish());
    }
    //endregion

}