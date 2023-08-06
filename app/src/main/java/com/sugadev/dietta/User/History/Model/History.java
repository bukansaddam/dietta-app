package com.sugadev.dietta.User.History.Model;

public class History {

    private int idHistory;
    private String title;
    private String description;
    private String date;
    private int totalExcercise;
    private int idUser;

    public History(int idHistory, String title, String description, String date, int totalExcercise, int idUser) {
        this.idHistory = idHistory;
        this.title = title;
        this.description = description;
        this.date = date;
        this.totalExcercise = totalExcercise;
        this.idUser = idUser;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalExcercise() {
        return totalExcercise;
    }

    public void setTotalExcercise(int totalExcercise) {
        this.totalExcercise = totalExcercise;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
