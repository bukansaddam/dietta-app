package com.sugadev.dietta.User.Schedule.Model;

import com.google.gson.annotations.SerializedName;


public class Schedule {

    @SerializedName("id_schedule")
    private int idSchedule;
    private String title;
    private String date;
    private int id_video;
    private int id_user;
    private int id_schedule_parent;
    private int version;

    public Schedule() {
    }

    public Schedule(int idSchedule, String title, String date, int id_video, int id_user, int id_schedule_parent, int version) {
        this.idSchedule = idSchedule;
        this.title = title;
        this.date = date;
        this.id_video = id_video;
        this.id_user = id_user;
        this.id_schedule_parent = id_schedule_parent;
        this.version = version;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_video() {
        return id_video;
    }

    public void setId_video(int id_video) {
        this.id_video = id_video;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_schedule_parent() {
        return id_schedule_parent;
    }

    public void setId_schedule_parent(int id_schedule_parent) {
        this.id_schedule_parent = id_schedule_parent;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
