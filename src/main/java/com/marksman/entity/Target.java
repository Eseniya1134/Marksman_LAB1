package com.marksman.entity;

public class Target extends Thread{

    private int xPos;
    private int yPos;
    private int size;
    private int speed;
    private boolean running = true;

    public Target(int xPos, int yPos, int size, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.speed = speed;
    }

    public void run(){
        while (running){
            verticalMovement();
            try {
                Thread.sleep(16); 
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void stopTarget() {
        running = false;
    }

    public void verticalMovement(){
        yPos += speed;
        if (yPos <= 0 || yPos >= 600){
            speed = -speed;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }






}