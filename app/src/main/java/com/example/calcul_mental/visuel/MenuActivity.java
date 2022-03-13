package com.example.calcul_mental.visuel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.calcul_mental.R;

public class MenuActivity extends AppCompatActivity {

    //region Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar bar = getSupportActionBar();
        if (bar != null)
            bar.hide();

        asssociateOpenActivityTobutton(R.id.play_button, JeuActivity.class);
        asssociateOpenActivityTobutton(R.id.score_button, ScoreActivity.class);
    }

    private void asssociateOpenActivityTobutton(int id , Class<?> activity) {
        Button button = findViewById(id);
        button.setOnClickListener(view -> openActivity(activity));
    }

    private void openActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity );
        startActivity(intent);
    }
    //endregion

}