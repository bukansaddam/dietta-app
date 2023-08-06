package com.sugadev.dietta.User.History.Model;

import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleHistoryParent;
import com.sugadev.dietta.User.UserProfile.Model.User;

public class HistoryParentDetail {

    public HistoryParent historyParent;
    public ScheduleHistoryParent scheduleHistoryParent;
    public User user;

    @Override
    public String toString() {
        return "HistoryParentDetail{" +
                "historyParent=" + historyParent +
                ", scheduleHistoryParent=" + scheduleHistoryParent +
                ", user=" + user +
                '}';
    }

    public HistoryParent getHistoryParent() {
        return historyParent;
    }

    public void setHistoryParent(HistoryParent historyParent) {
        this.historyParent = historyParent;
    }

    public ScheduleHistoryParent getScheduleHistoryParent() {
        return scheduleHistoryParent;
    }

    public void setScheduleHistoryParent(ScheduleHistoryParent scheduleHistoryParent) {
        this.scheduleHistoryParent = scheduleHistoryParent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
