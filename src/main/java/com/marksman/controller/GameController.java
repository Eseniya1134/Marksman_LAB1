package com.marksman.controller;

import com.marksman.model.GameState;
import com.marksman.model.SaveManager;

import java.io.IOException;
import javax.swing.Timer;

import static java.lang.Math.max;

public class GameController {

    private Timer gameTimer;
    private GameState state;
    private SaveManager saveManager;
    private static final int MAX_SHOTS = 40;
    private static final int GAME_TIME = 60;
    private int timeLeft = GAME_TIME;

    public GameController(){
        this.saveManager = new SaveManager();
        this.state = saveManager.load();
    }

    public void startGame(){
        resetTime();
        state.reset();

        startTimer();

        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopGame(){
        stopTimer();
        state.stop();

        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void finishGame(){
        stopTimer();
        state.finish();

        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resumeGame() {
        state.setActive(true);
        state.setPaused(false);

        startTimer();

        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPoint(int points) {
        int ourPoint = state.getPoint();
        int maxPoint = state.getRecordPoint();

        state.setPoint(ourPoint + points);
        state.setRecordPoint(max(maxPoint, ourPoint + points));

        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addShot(){
        int ourShot = state.getShot();
        state.setShot(ourShot + 1);

        if (ourShot + 1 == MAX_SHOTS){
            finishGame();
        }

        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameState getState() {
        return state;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void decreaseTime() {
        timeLeft--;
    }

    public void resetTime() {
        timeLeft = GAME_TIME;
    }

    private void startTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
        }

        gameTimer = new Timer(1000, e -> {
            if (state.isActive() && timeLeft > 0) {
                decreaseTime();
                System.out.println("Time left: " + timeLeft);

                onTimeUpdated();

                if (timeLeft <= 0) {
                    finishGame();
                }
            }
        });

        gameTimer.start();
    }

    private void stopTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
            gameTimer = null;
        }
    }

    private void onTimeUpdated() {
    }
}