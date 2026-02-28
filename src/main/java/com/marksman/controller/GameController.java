package com.marksman.controller;

import com.marksman.model.GameState;
import com.marksman.model.SaveManager;

import java.io.IOException;

import static java.lang.Math.max;

public class GameController {

    private GameState state;
    private SaveManager saveManager;
    private static final int MAX_SHOTS = 20;

    public GameController(){
        this.saveManager = new SaveManager();
        this.state = saveManager.load();
    }

    public void startGame(){
        state.reset();
        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopGame(){
        state.stop();
        try {
            saveManager.save(state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void finishGame(){
        state.finish();
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

}
