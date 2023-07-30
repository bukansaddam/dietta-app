package com.sugadev.dietta.Model;

import com.google.gson.annotations.SerializedName;

public class Culinary {

    private int idCulinary;
    @SerializedName("foodname")
    private String title;
    private int kalori;
    private int lemak;
    private int karbohidrat;
    private int protein;
    private String deskripsi;
    @SerializedName("imgurl")
    private String thumbnail;

    public Culinary() {
    }

    public Culinary(int idCulinary, String title, int kalori, int lemak, int karbohidrat, int protein, String deskripsi, String thumbnail) {
        this.idCulinary = idCulinary;
        this.title = title;
        this.kalori = kalori;
        this.lemak = lemak;
        this.karbohidrat = karbohidrat;
        this.protein = protein;
        this.deskripsi = deskripsi;
        this.thumbnail = thumbnail;
    }

    public int getIdCulinary() {
        return idCulinary;
    }

    public void setIdCulinary(int idCulinary) {
        this.idCulinary = idCulinary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getKalori() {
        return kalori;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }

    public int getLemak() {
        return lemak;
    }

    public void setLemak(int lemak) {
        this.lemak = lemak;
    }

    public int getKarbohidrat() {
        return karbohidrat;
    }

    public void setKarbohidrat(int karbohidrat) {
        this.karbohidrat = karbohidrat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
