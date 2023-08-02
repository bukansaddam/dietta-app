package com.sugadev.dietta.User.Schedule.Model;

import com.sugadev.dietta.User.Schedule.ScheduleDetail;
import com.sugadev.dietta.User.Users;
import com.sugadev.dietta.User.Video.Model.Video;

public class Schedul {

    public Users users;

    @Override
    public String toString() {
        return "Schedul{" +
                "users=" + users +
                ", schedule=" + schedule +
                ", video=" + video +
                ", scheduleDetailParent=" + scheduleDetailParent +
                '}';
    }

    public Schedule schedule;
    public Video video;
    public ScheduleDetailParent scheduleDetailParent;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public ScheduleDetailParent getScheduleDetailParent() {
        return scheduleDetailParent;
    }

    public void setScheduleDetailParent(ScheduleDetailParent scheduleDetailParent) {
        this.scheduleDetailParent = scheduleDetailParent;
    }
}
