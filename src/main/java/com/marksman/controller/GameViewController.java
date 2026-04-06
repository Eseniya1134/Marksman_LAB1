package com.marksman.controller;

import com.marksman.entity.Arrow;
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
    @FXML private Label timeValue;

    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button shotBtn;

    @FXML private GameField gameField;

    private GameController gameController;
    private Target nearTarget;
    private Target farTarget;
    private Arrow arrow;
    private AnimationTimer gameLoop;
    private boolean flag = false;

    @FXML
    public void initialize() {
        gameController = new GameController();

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameField.render();
                gameField.drawPlayer();

                if (nearTarget != null) gameField.drawTarget(nearTarget);
                if (farTarget != null) gameField.drawTarget(farTarget);
                if (arrow != null) gameField.drawArrow(arrow);

                updateUI();

                // завершение игры по таймеру
                if (gameController.getTimeLeft() <= 0 && flag) {
                    endGameByTime();
                    return;
                }

                // проверки попаданий
                if (arrow != null) {
                    if (nearTarget != null && nearTarget.isHit(arrow)) {
                        gameController.addPoint(1);
                        arrow.stopArrow();
                        arrow = null;
                    } else if (farTarget != null && farTarget.isHit(arrow)) {
                        gameController.addPoint(2);
                        arrow.stopArrow();
                        arrow = null;
                    }
                }
            }
        };

        gameLoop.start();
        gameField.render();
    }

    @FXML
    private void onStartClick() {
        flag = true;

        gameController.startGame();
        onCreateTerget();

        pauseBtn.setText("Пауза");

        updateUI();
    }

    @FXML
    private void onPauseClick() {

        // ИГРА ИДЕТ \ СТАВИМ НА ПАУЗУ
        if (gameController.getState().isActive()) {

            flag = false;

            if (nearTarget != null) nearTarget.stopTarget();
            if (farTarget != null) farTarget.stopTarget();
            if (arrow != null) arrow.stopArrow();

            gameController.stopGame();

            pauseBtn.setText("Продолжить");
        }

        // ИГРА НА ПАУЗЕ \ ПРОДОЛЖАЕМ
        else if (gameController.getState().isPaused()) {

            flag = true;

            gameController.resumeGame();
            onCreateTerget();

            pauseBtn.setText("Пауза");
        }

        updateUI();
    }

    @FXML
    private void onShotClick() {
        if (flag) {
            gameController.addShot();

            if (arrow != null) arrow.stopArrow();

            arrow = new Arrow(100, 283, 70, 5);
            arrow.start();

            updateUI();
        }
    }

    private void updateUI() {
        pointValue.setText(String.valueOf(gameController.getState().getPoint()));
        shotValue.setText(String.valueOf(gameController.getState().getShot()));
        recordValue.setText(String.valueOf(gameController.getState().getRecordPoint()));
        timeValue.setText(String.valueOf(gameController.getTimeLeft()));
    }

    private void onCreateTerget() {
        if (nearTarget != null) nearTarget.stopTarget();
        if (farTarget != null) farTarget.stopTarget();

        this.nearTarget = new Target(400, 300, 100, 3, false);
        this.farTarget = new Target(650, 300, 50, 6, true);

        nearTarget.start();
        farTarget.start();
    }

    private void endGameByTime() {
        flag = false;

        if (nearTarget != null) nearTarget.stopTarget();
        if (farTarget != null) farTarget.stopTarget();
        if (arrow != null) arrow.stopArrow();

        nearTarget = null;
        farTarget = null;
        arrow = null;

        gameController.finishGame();

        pauseBtn.setText("Пауза");

        updateUI();

        System.out.println("Game Over (time)");
    }
}