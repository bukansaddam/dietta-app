package com.sugadev.dietta.User.Schedule.Model.Parent;

import com.google.gson.annotations.SerializedName;

public class ScheduleHistoryParent {

    @SerializedName("idScheHistoryParentDTO")
    private int idScheHistoryParent;
    private String title;
    private String description;
    private int id_user;
    private int version;

    public ScheduleHistoryParent(int idScheHistoryParent, String title, String description, int id_user, int version) {
        this.idScheHistoryParent = idScheHistoryParent;
        this.title = title;
        this.description = description;
        this.id_user = id_user;
        this.version = version;
    }

    public int getIdScheHistoryParent() {
        return idScheHistoryParent;
    }

    public void setIdScheHistoryParent(int idScheHistoryParent) {
        this.idScheHistoryParent = idScheHistoryParent;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
