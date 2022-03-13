package com.example.calcul_mental.visuel.adaptaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.calcul_mental.R;
import com.example.calcul_mental.bdd.ScoreModel;
import com.example.calcul_mental.logique.TimeModel;

import java.util.Calendar;
import java.util.LinkedList;

public class ScoreAdaptater extends ArrayAdapter<ScoreModel> {
    private final Context context;
    private LinkedList<ScoreModel> scores;

    public ScoreAdaptater(Context context, int resource, LinkedList<ScoreModel> scores) {
        super(context, resource, scores);
        this.context = context;
        this.scores = scores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adaptater_score, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        ScoreModel score = scores.get(position);

        TextView idText = convertView.findViewById(R.id.id_text);
        TextView nbOperationText = convertView.findViewById(R.id.nb_operations_text);
        TextView nbTriesText = convertView.findViewById(R.id.nb_tries_text);
        TextView timeText = convertView.findViewById(R.id.time_text);

        TimeModel time = score.getTime();
        String result = context.getString(R.string.timer, time.getMinutes(), time.getSecondes());

        idText.setText(Integer.toString(position));
        nbOperationText.setText(Integer.toString(score.nbOperations));
        nbTriesText.setText(Integer.toString(score.nbTries));
        timeText.setText(result);

        return convertView;
    }
}
