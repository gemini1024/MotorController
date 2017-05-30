package com.smartsoftware.eh.motorcontroller.vo;

/**
 * Created by jh on 17. 5. 29.
 */

public class MotorInfo {
    private int id;
    private int gid;
    private String content;
    private int currentAction;

    public MotorInfo(int id, int gid, String content, int currentAction) {
        this.id = id;
        this.gid = gid;
        this.content = content;
        this.currentAction = currentAction;
    }

    public static int makeId(int gid, int id) {
        return Integer.parseInt(String.format("%03d%03d", gid, id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    @Override
    public String toString() {
        return "Details about Motor - " + content +
                "\nID : " + String.format("%06d", id) +
                "\nGID : " + gid +
                "\nCurrent Action : " + ((currentAction == MotorActions.PLAY)? "PLAYED" : "STOPPED");
    }
}
