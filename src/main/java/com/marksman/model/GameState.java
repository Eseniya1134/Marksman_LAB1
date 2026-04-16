package com.marksman.model;

import java.util.prefs.Preferences;

public class GameState {

    private static final Preferences PREFS =
            Preferences.userNodeForPackage(GameState.class);

    private static final String KEY_SAVED  = "hasSave";
    private static final String KEY_POINT  = "point";
    private static final String KEY_SHOT   = "shot";
    private static final String KEY_TIME   = "time";
    private static final String KEY_RECORD = "recordPoint";

    // состояние игры
    private static int point;
    private static int shot;
    private static int time;

    private static int recordPoint;

    private static boolean active;
    private static boolean paused;

    public GameState() {

        this.recordPoint = PREFS.getInt(KEY_RECORD, 0);

        // не сбрасываем состояние если есть сохранённая игра
        if (!hasSavedGame()) {
            this.point = 0;
            this.shot = 0;
            this.time = 60;
            this.active = false;
            this.paused = false;
        }
    }


    //ЛОГИКА

    public static void reset() {
        point = 0;
        shot = 0;
        time = 60;

        active = true;
        paused = false;

        PREFS.putBoolean(KEY_SAVED, false);
    }

    public void stop() {
        paused = true;
        active = false;
        save();
    }

    public static void finish() {
        active = false;
        paused = false;

        if (point > recordPoint) {
            recordPoint = point;
            PREFS.putInt(KEY_RECORD, recordPoint);
        }

        PREFS.putBoolean(KEY_SAVED, false);
    }

    //СОХРАНЕНИЕ

    public void save() {
        PREFS.putBoolean(KEY_SAVED, true);
        PREFS.putInt(KEY_POINT, point);
        PREFS.putInt(KEY_SHOT, shot);
        PREFS.putInt(KEY_TIME, time);
    }

    public static void load() {
        point = PREFS.getInt(KEY_POINT, 0);
        shot  = PREFS.getInt(KEY_SHOT, 0);
        time  = PREFS.getInt(KEY_TIME, 60);
        active = false;
        paused = true;
    }

    public static boolean hasSavedGame() {
        return PREFS.getBoolean(KEY_SAVED, false);
    }

    //GETTERS / SETTERS

    public static int getPoint() { return point; }
    public int getShot()  { return shot; }
    public int getTime()  { return time; }
    public int getRecordPoint() { return recordPoint; }

    public void setPoint(int point) { this.point = point; }
    public void setShot(int shot)   { this.shot = shot; }
    public void setTime(int time)   { this.time = time; }

    public boolean getActive() { return active; }
    public boolean getPaused() { return paused; }
    public void setActive(boolean active) { this.active = active;}
    public void setPaused(boolean paused) { this.paused = paused;}

    public void setRecordPoint(int max) { this.recordPoint = recordPoint;}


}