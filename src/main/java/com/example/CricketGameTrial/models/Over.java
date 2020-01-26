package com.example.CricketGameTrial.models;

import java.util.ArrayList;

public class Over {
    private ArrayList<Character> balls = new ArrayList<>(); // Stores statistics of each ball

    public ArrayList<Character> getBalls() {
        return balls;
    }

    public void addBalls(Character r) {
        balls.add(r);
    }
}
