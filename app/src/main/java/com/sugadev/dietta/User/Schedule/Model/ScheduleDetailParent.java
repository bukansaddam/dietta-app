package com.sugadev.dietta.User.Schedule.Model;

import androidx.annotation.NonNull;

import com.sugadev.dietta.User.Users;
import com.sugadev.dietta.User.Video.Model.Video;

public class ScheduleDetailParent {

    public Users users;
    public ScheduleParent scheduleParent;

    public Schedule scheduleChild;

    @Override
    public String toString() {
        return "ScheduleDetailParent{" +
                "users=" + users.toString() +
                ", scheduleParent=" + scheduleParent.toString() +
                ", scheduleChild=" + scheduleChild.toString() +
                '}';
    }

    public ScheduleDetailParent() {
    }

    public ScheduleDetailParent(Users users, ScheduleParent scheduleParent, Schedule scheduleChild) {
        this.users = users;
        this.scheduleParent = scheduleParent;
        this.scheduleChild = scheduleChild;
    }

    public Schedule getScheduleChild() {
        return scheduleChild;
    }

    public void setScheduleChild(Schedule scheduleChild) {
        this.scheduleChild = scheduleChild;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public ScheduleParent getScheduleParent() {
        return scheduleParent;
    }

    public void setScheduleParent(ScheduleParent scheduleParent) {
        this.scheduleParent = scheduleParent;
    }


}
