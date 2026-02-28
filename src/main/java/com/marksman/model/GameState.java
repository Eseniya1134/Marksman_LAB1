package com.marksman.model;

public class GameState {

    private int point;
    private int shot;
    private int recordPoint;

    private boolean active;
    private boolean paused;   //игру можно продолжить (флаг о сохранении прошлой игры)

    public GameState(){
        point = 0;
        shot = 0;
        active = false;
        paused = false;
    }

    public void reset(){ //игру нельзя продолжить, она начинается с самого начала
        this.paused = false;
        this.active = true;
        this.point = 0;
        this.shot = 0;
    }

    public void stop(){ //игру остановили, но возможно продолжить
        this.paused = true;
        this.active = false;
    }

    public void finish(){ //игру нельзя продолжить, игру сразу закончили
        this.paused = false;
        this.active = false;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getShot() {
        return shot;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }

    public int getRecordPoint() {
        return recordPoint;
    }

    public void setRecordPoint(int recordPoint) {
        this.recordPoint = recordPoint;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }





}
