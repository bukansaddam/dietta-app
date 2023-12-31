package com.sugadev.dietta.User.Diary.Model;

import com.google.gson.annotations.SerializedName;

public class Diary {
    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", tanggal='" + tanggal + '\'' +
                ", idUser=" + idUser +
                ", idCulinary=" + idCulinary +
                ", foodname='" + foodname + '\'' +
                ", kalori=" + kalori +
                ", url='" + url + '\'' +
                '}';
    }

    @SerializedName("idDiary")
    private int id;
    private String name;
    private String desc;
    private String tanggal;
    private int idUser;
    private int idCulinary;
    private String foodname;
    private int kalori;
    @SerializedName("imgurl")
    private String url;

    public Diary() {
    }

    public Diary(int id, String name, String desc, String tanggal, int idUser, int idCulinary, String foodname, int kalori, String url) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.tanggal = tanggal;
        this.idUser = idUser;
        this.idCulinary = idCulinary;
        this.foodname = foodname;
        this.kalori = kalori;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getKalori() {
        return kalori;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCulinary() {
        return idCulinary;
    }

    public void setIdCulinary(int idCulinary) {
        this.idCulinary = idCulinary;
    }
}
