package com.sugadev.dietta.Admin;

public class Video {

    String url, title, deskripsi;

    public Video(String url, String title, String deskripsi) {
        this.url = url;
        this.title = title;
        this.deskripsi = deskripsi;
    }

    public Video() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
