package com.sugadev.dietta.User.History.Model;

import com.google.gson.annotations.SerializedName;

public class HistoryParent {

    private int idHistoryParent;
    private String title;
    private String description;
    private int idUser;
    private int idScheHistoryParent;

    public HistoryParent(int idHistoryParent, String title, String description, int idUser, int idScheHistoryParent) {
        this.idHistoryParent = idHistoryParent;
        this.title = title;
        this.description = description;
        this.idUser = idUser;
        this.idScheHistoryParent = idScheHistoryParent;
    }

    public int getIdHistoryParent() {
        return idHistoryParent;
    }

    public void setIdHistoryParent(int idHistoryParent) {
        this.idHistoryParent = idHistoryParent;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdScheHistoryParent() {
        return idScheHistoryParent;
    }

    public void setIdScheHistoryParent(int idScheHistoryParent) {
        this.idScheHistoryParent = idScheHistoryParent;
    }
}
