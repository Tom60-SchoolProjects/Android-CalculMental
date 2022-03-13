package com.example.calcul_mental.bdd;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.calcul_mental.R;
import com.example.calcul_mental.logique.TimeModel;

import java.io.Serializable;
import java.util.*;

public class ScoreModel implements Serializable {
    public int nbOperations = 0;
    public int nbTries = 0;
    public long time = 0;

    public TimeModel getTime() {
        Date time = new Date(this.time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        return new TimeModel(this.time / 1000 / 60, calendar.get(Calendar.SECOND)) ;
    }
}
