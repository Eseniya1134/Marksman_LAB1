package com.marksman.controller;

import com.marksman.model.GameState;
import com.marksman.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    @FXML private Label resultLabel;
    @FXML private Label scoreLabel;
    @FXML private ImageView img;

    private static final int WIN_THRESHOLD = 10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int score = GameState.getPoint();

        if (score > WIN_THRESHOLD) {
            img.setImage(new Image(getClass().getResourceAsStream("/com/marksman/images/gameWin.png")));
            resultLabel.setText("ТЫ ВЫИГРАЛ!");
            resultLabel.setId("resultWin");
            scoreLabel.setText("НАБРАЛ " + score + " ОЧКОВ");
        } else {
            img.setImage(new Image(getClass().getResourceAsStream("/com/marksman/images/gameOver.png")));
            resultLabel.setText("ТЫ ПРОИГРАЛ!");
            resultLabel.setId("resultLose");
            scoreLabel.setText("НЕ НАБРАЛ ДАЖЕ 10...");
        }

        GameState.finish();
    }

    @FXML
    private void onRestartClick() {
        GameState.reset();
        SceneManager.switchTo("/com/marksman/game.fxml");
    }

    @FXML
    private void onMenuClick() {
        SceneManager.switchTo("/com/marksman/menuView.fxml");
    }
}