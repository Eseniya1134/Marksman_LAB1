package com.marksman.controller;

import com.marksman.entity.Target;
import com.marksman.view.GameField;
import javafx.animation.AnimationTimer;
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

    @FXML private GameField gameField;

    private GameController gameController;
    private Target nearTarget;
    private Target farTarget;
    private AnimationTimer gameLoop;

    @FXML
    public void initialize() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameField.render();
                if (nearTarget != null) gameField.drawTarget(nearTarget);
                if (farTarget != null) gameField.drawTarget(farTarget);
            }
        };
        gameLoop.start();
        gameController = new GameController();
        gameField.render();
    }

    @FXML
    private void onStartClick() {
        gameController.startGame();
        onCreateTerget();
        updateUI();
    }

    @FXML
    private void onPauseClick() {
        gameController.stopGame();
        updateUI();
    }

    @FXML
    private void onShotClick() {
        gameController.addShot();
        updateUI();
    }

    private void updateUI() {
        pointValue.setText(String.valueOf(gameController.getState().getPoint()));
        shotValue.setText(String.valueOf(gameController.getState().getShot()));
        recordValue.setText(String.valueOf(gameController.getState().getRecordPoint()));
    }

    private void onCreateTerget(){
        if (nearTarget != null) nearTarget.stopTarget();
        if (farTarget != null) farTarget.stopTarget();
        this.nearTarget = new Target(500, 300, 70, 10);
        this.farTarget = new Target(800, 300, 35, 20);
        nearTarget.start();
        farTarget.start();
    }



}