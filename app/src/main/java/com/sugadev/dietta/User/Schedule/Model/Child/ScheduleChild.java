package com.sugadev.dietta.User.Schedule.Model.Child;

import com.google.gson.annotations.SerializedName;


public class ScheduleChild {

    @SerializedName("id_schedule_child")
    private int idScheduleChild;
    private int id_video;
    private int id_schedule_parent;
    private int version;

    public ScheduleChild() {
    }

    public ScheduleChild(int idScheduleChild, int id_video, int id_schedule_parent, int version) {
        this.idScheduleChild = idScheduleChild;
        this.id_video = id_video;
        this.id_schedule_parent = id_schedule_parent;
        this.version = version;
    }

    public int getIdScheduleChild() {
        return idScheduleChild;
    }

    public void setIdScheduleChild(int idScheduleChild) {
        this.idScheduleChild = idScheduleChild;
    }

    public int getId_video() {
        return id_video;
    }

    public void setId_video(int id_video) {
        this.id_video = id_video;
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
