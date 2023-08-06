package com.sugadev.dietta.User.History.Model;

import com.google.gson.annotations.SerializedName;

public class HistoryChild {

    @SerializedName("id_history_child")
    private int idHistoryChild;
    @SerializedName("id_history_parent")
    private int idHistoryParent;
    private String title;
    @SerializedName("total_time")
    private String totalTime;
    @SerializedName("kalori_terbakar")
    private int burnCalories;
    @SerializedName("id_sche_history_child")
    private int idScheHistoryChild;

    public HistoryChild(int idHistoryChild, int idHistoryParent, String title, String totalTime, int burnCalories, int idScheHistoryChild) {
        this.idHistoryChild = idHistoryChild;
        this.idHistoryParent = idHistoryParent;
        this.title = title;
        this.totalTime = totalTime;
        this.burnCalories = burnCalories;
        this.idScheHistoryChild = idScheHistoryChild;
    }

    public int getIdHistoryChild() {
        return idHistoryChild;
    }

    public void setIdHistoryChild(int idHistoryChild) {
        this.idHistoryChild = idHistoryChild;
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

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public int getBurnCalories() {
        return burnCalories;
    }

    public void setBurnCalories(int burnCalories) {
        this.burnCalories = burnCalories;
    }

    public int getIdScheHistoryChild() {
        return idScheHistoryChild;
    }

    public void setIdScheHistoryChild(int idScheHistoryChild) {
        this.idScheHistoryChild = idScheHistoryChild;
    }
}
