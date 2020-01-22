package com.example.CricketGameTrial.service;

import java.util.ArrayList;

class Over {
    private ArrayList<Integer> balls = new ArrayList<>(); // Stores statistics of each ball

    public ArrayList<Integer> getBalls() {
        return balls;
    }

    public void addBalls(int r) {
        balls.add(r);
    }
}
