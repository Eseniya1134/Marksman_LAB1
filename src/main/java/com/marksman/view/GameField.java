package com.marksman.view;

import com.marksman.entity.Arrow;
import com.marksman.entity.Target;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameField extends Canvas {

    private Image bgImage;
    private Image target1Image;
    private Image target2Image;
    private Image arrowImage;
    private Image playerImage;

    public GameField() {
        super(900, 600);
        bgImage = new Image(getClass().getResourceAsStream("/com/marksman/images/cloud.png"));
        target1Image = new Image(getClass().getResourceAsStream("/com/marksman/images/target1.png"));
        target2Image = new Image(getClass().getResourceAsStream("/com/marksman/images/target2.png"));
        arrowImage = new Image(getClass().getResourceAsStream("/com/marksman/images/arrow.png"));
        playerImage = new Image(getClass().getResourceAsStream("/com/marksman/images/angel.png"));
    }

    public GameField(double width, double height) {
        super(width, height);
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.drawImage(bgImage, 0, 0, getWidth(), getHeight());
    }

    public void drawTarget(Target target) {
        GraphicsContext gc = getGraphicsContext2D();
        if (target.isFar()) {
            gc.drawImage(target2Image, target.getxPos(), target.getyPos(), target.getSize(), target.getSize()*1.25);
        } else {
            gc.drawImage(target1Image, target.getxPos(), target.getyPos(), target.getSize(), target.getSize()*1.25);
        }
    }

    public void drawArrow(Arrow arrow) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.drawImage(arrowImage, arrow.getxPos(), arrow.getyPos(), arrow.getSize(), arrow.getSize());
    }

    public void drawPlayer() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.drawImage(playerImage, 20, 250, 80, 100);
    }
}