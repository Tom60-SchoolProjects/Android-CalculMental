package com.example.calcul_mental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Associer une action à mon bouton !!

        //Button monBouton = findViewById(R.id.playbutton);

        //monBouton.setOnClickListener(view -> openJeu());


        asssociateOpenActivityTobutton(R.id.compute_button,Jeu.class);
        asssociateOpenActivityTobutton(R.id.resultbutton,Score.class);
    }

    private void asssociateOpenActivityTobutton(int id , Class activity)
    {
        Button button = findViewById(id);
        button.setOnClickListener(view -> OpenActivity(activity));
    }

    private void OpenActivity(){
        Intent intent = new Intent(this,Jeu.class);
        startActivity(intent);

    }

    private void OpenActivity(Class activity){
        Intent intent = new Intent(this, activity );
        startActivity(intent);
    }


}