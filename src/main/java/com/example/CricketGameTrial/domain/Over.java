package com.example.CricketGameTrial.domain;

import java.util.ArrayList;

public class Over {
    private ArrayList<Integer> balls = new ArrayList<>(); // Stores statistics of each ball

    public ArrayList<Integer> getBalls() {
        return balls;
    }

    public void addBalls(int r) {
        balls.add(r);
    }
}
