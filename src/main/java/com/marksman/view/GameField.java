package com.marksman.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameField extends Canvas {

    public GameField() {
        super(900, 600);
    }

    public GameField(double width, double height) {
        super(width, height);
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight()); // очищаем поле
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, getWidth(), getHeight()); // заливаем фон
    }


}
