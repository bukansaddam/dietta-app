package com.sugadev.dietta.User.Schedule.Model;

import com.google.gson.annotations.SerializedName;

public class ScheduleParent {

    @SerializedName("idScheduleParent")
    private int idScheParent;
    private String title;
    private int id_schedule;
    private int id_user;

    public ScheduleParent() {
    }

    public ScheduleParent(int idScheParent, String title, int id_schedule, int id_user) {
        this.idScheParent = idScheParent;
        this.title = title;
        this.id_schedule = id_schedule;
        this.id_user = id_user;
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

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
