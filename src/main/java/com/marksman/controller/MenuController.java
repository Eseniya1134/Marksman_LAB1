package com.marksman.controller;

import com.marksman.model.GameState;
import com.marksman.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private Button continueBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Кнопка "Продолжить" — только если есть сохранённая игра
        boolean hasSave = GameState.hasSavedGame();
        continueBtn.setVisible(hasSave);
        continueBtn.setManaged(hasSave);
    }

    @FXML
    private void onNewGameClick() {
        GameState.reset();
        SceneManager.switchTo("/com/marksman/game.fxml");
    }

    @FXML
    private void onContinueClick() {
        GameState.load();                          // восстанавливаем очки / время
        SceneManager.switchTo("/com/marksman/game.fxml");
    }
}