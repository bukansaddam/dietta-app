package com.sugadev.dietta.User.Diary;

import com.google.gson.annotations.SerializedName;
import com.sugadev.dietta.User.Culinary.Culinary;

public class DiaryDetail {

    private Diary diary;

    private Culinary culinary;

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public Culinary getCulinary() {
        return culinary;
    }

    public void setCulinary(Culinary culinary) {
        this.culinary = culinary;
    }

    @Override
    public String toString() {
        return "DiaryDetail{" +
                "diary=" + diary.toString() +
                ", culinary=" + culinary.toString() +
                '}';
    }
}
