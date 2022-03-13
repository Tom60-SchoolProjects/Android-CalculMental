package com.example.calcul_mental.logique;

public class TimeModel {
    private long minutes;
    private long secondes;

    public TimeModel(long minutes, long secondes) {
        this.minutes = minutes;
        this.secondes = secondes;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getSecondes() {
        return secondes;
    }
}
