package com.sugadev.dietta.User.Schedule.Model;

public class ScheduleHistory {

    private int idScheHistory;
    private int id_schedule;
    private String title;
    private String date;
    private int id_video;
    private int id_user;
    private int version;

    @Override
    public String toString() {
        return "ScheduleHistory{" +
                "idScheHistory=" + idScheHistory +
                ", id_schedule=" + id_schedule +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", id_video=" + id_video +
                ", id_user=" + id_user +
                ", version=" + version +
                '}';
    }

    public ScheduleHistory(int idScheHistory, int id_schedule, String title, String date, int id_video, int id_user, int version) {
        this.idScheHistory = idScheHistory;
        this.id_schedule = id_schedule;
        this.title = title;
        this.date = date;
        this.id_video = id_video;
        this.id_user = id_user;
        this.version = version;
    }

    public int getIdScheHistory() {
        return idScheHistory;
    }

    public void setIdScheHistory(int idScheHistory) {
        this.idScheHistory = idScheHistory;
    }

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
