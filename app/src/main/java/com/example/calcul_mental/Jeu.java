package com.example.calcul_mental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Jeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        Button validateButton = findViewById(R.id.validate_operation);
    }
}