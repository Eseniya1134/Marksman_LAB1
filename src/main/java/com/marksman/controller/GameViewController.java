package com.marksman.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameViewController {

    @FXML private Label pointValue;
    @FXML private Label shotValue;
    @FXML private Label recordValue;

    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button shotBtn;

    private GameController gameController;

    @FXML
    public void initialize() {
        gameController = new GameController();
    }

    @FXML
    private void onStartClick() {
        gameController.startGame();
        updateUI();
    }

    @FXML
    private void onPauseClick() {}

    @FXML
    private void onShotClick() {}

    private void updateUI() {
        pointValue.setText(String.valueOf(gameController.getState().getPoint()));
        shotValue.setText(String.valueOf(gameController.getState().getShot()));
        recordValue.setText(String.valueOf(gameController.getState().getRecordPoint()));
    }


}