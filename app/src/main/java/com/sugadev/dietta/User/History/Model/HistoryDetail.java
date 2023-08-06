package com.sugadev.dietta.User.History.Model;

import com.google.gson.annotations.SerializedName;
import com.sugadev.dietta.User.Schedule.Model.ScheduleHistory;
import com.sugadev.dietta.User.UserProfile.Model.User;

public class HistoryDetail {

    @Override
    public String toString() {
        return "HistoryModel{" +
                "userProfile=" + userProfile +
                ", historyModel=" + history +
                ", scheduleParent=" + scheduleHistory +
                '}';
    }

    @SerializedName("user")
    public User userProfile;
    public History history;
    public ScheduleHistory scheduleHistory;

    public HistoryDetail(User userProfile, History history, ScheduleHistory scheduleHistory) {
        this.userProfile = userProfile;
        this.history = history;
        this.scheduleHistory = scheduleHistory;
    }

    public User getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }

    public History getHistoryModel() {
        return history;
    }

    public void setHistoryModel(History history) {
        this.history = history;
    }

    public ScheduleHistory getScheduleHistory() {
        return scheduleHistory;
    }

    public void setScheduleHistory(ScheduleHistory scheduleHistory) {
        this.scheduleHistory = scheduleHistory;
    }
}
