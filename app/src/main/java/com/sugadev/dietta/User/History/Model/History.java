package com.sugadev.dietta.User.History.Model;

public class History {

    private int idHistory;
    private String title;
    private String totaltime;
    private int burncalories;
    private int idUser;
    private int idScheduleHistory;

    public History(int idHistory, String title, String totaltime, int burncalories, int idUser, int idScheduleHistory) {
        this.idHistory = idHistory;
        this.title = title;
        this.totaltime = totaltime;
        this.burncalories = burncalories;
        this.idUser = idUser;
        this.idScheduleHistory = idScheduleHistory;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public int getBurncalories() {
        return burncalories;
    }

    public void setBurncalories(int burncalories) {
        this.burncalories = burncalories;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdScheduleHistory() {
        return idScheduleHistory;
    }

    public void setIdScheduleHistory(int idScheduleHistory) {
        this.idScheduleHistory = idScheduleHistory;
    }
}
