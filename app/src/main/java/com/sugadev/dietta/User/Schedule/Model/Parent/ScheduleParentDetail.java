package com.sugadev.dietta.User.Schedule.Model.Parent;

import com.sugadev.dietta.User.Schedule.Model.Child.ScheduleChild;
import com.sugadev.dietta.User.UserProfile.Model.User;

public class ScheduleParentDetail {

    public User user;
    public ScheduleParent scheduleParent;

    public ScheduleChild schedule;

    @Override
    public String toString() {
        return "ScheduleDetailParent{" +
                "users=" + user.toString() +
                ", scheduleParent=" + scheduleParent.toString() +
                ", scheduleChild=" + schedule.toString() +
                '}';
    }

    public ScheduleParentDetail() {
    }

    public ScheduleParentDetail(User user, ScheduleParent scheduleParent, ScheduleChild scheduleChild) {
        this.user = user;
        this.scheduleParent = scheduleParent;
        this.schedule = scheduleChild;
    }

    public ScheduleChild getScheduleChild() {
        return schedule;
    }

    public void setScheduleChild(ScheduleChild scheduleChild) {
        this.schedule = scheduleChild;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }

    public ScheduleParent getScheduleParent() {
        return scheduleParent;
    }

    public void setScheduleParent(ScheduleParent scheduleParent) {
        this.scheduleParent = scheduleParent;
    }


}
