package com.sugadev.dietta.User.Schedule.Model.Parent;

import com.google.gson.annotations.SerializedName;

public class ScheduleParent {

    @SerializedName("id_schedule_parent")
    private int idScheParent;
    private String title;
    private String description;
    private int id_user;

    public ScheduleParent() {
    }

    public ScheduleParent(int idScheParent, String title, String description, int id_user) {
        this.idScheParent = idScheParent;
        this.title = title;
        this.description = description;
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getIdScheParent() {
        return idScheParent;
    }

    public void setIdScheParent(int idScheParent) {
        this.idScheParent = idScheParent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
