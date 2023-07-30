package com.sugadev.dietta.User.Video.Model;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id_video")
    private int id;
    private String title;
    private String description;
    private String category;
    private String url;
    private String thumbnail;

    public Video() {
    }

    public Video(int id, String title, String description, String category, String url, String thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.url = url;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
