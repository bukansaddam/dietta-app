package com.sugadev.dietta.User.Schedule.Model.Child;

import com.google.gson.annotations.SerializedName;
import com.sugadev.dietta.User.Schedule.Model.Parent.ScheduleParentDetail;
import com.sugadev.dietta.User.UserProfile.Model.User;
import com.sugadev.dietta.User.Video.Model.Video;

public class ScheduleChildDetail {

    @Override
    public String toString() {
        return "ScheduleChildDetail{" +
                "scheduleChild=" + scheduleChild +
                ", video=" + video +
                ", scheduleParentDetail=" + scheduleParentDetail +
                '}';
    }

    @SerializedName("schedule")
    public ScheduleChild scheduleChild;
    public Video video;
    @SerializedName("scheduleParent")
    public ScheduleParentDetail scheduleParentDetail;


    public ScheduleChild getSchedule() {
        return scheduleChild;
    }

    public void setSchedule(ScheduleChild scheduleChild) {
        this.scheduleChild = scheduleChild;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public ScheduleParentDetail getScheduleDetailParent() {
        return scheduleParentDetail;
    }

    public void setScheduleDetailParent(ScheduleParentDetail scheduleParentDetail) {
        this.scheduleParentDetail = scheduleParentDetail;
    }
}
