package com.sugadev.dietta.User.Diary.Model;

import com.sugadev.dietta.User.Culinary.Model.Culinary;
import com.sugadev.dietta.User.Diary.Model.Diary;

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
