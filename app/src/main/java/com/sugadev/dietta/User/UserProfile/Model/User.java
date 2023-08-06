package com.sugadev.dietta.User.UserProfile.Model;


import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class User {

    private int idUser;
    private String username;
    private String password;
    private String name;
    private String email;
    @SerializedName("noTelp")
    private int no_telp;
    private int beratBadan;
    private int tinggiBadan;

    public User(int idUser, String username, String password, String name, String email, int no_telp, int beratBadan, int tinggiBadan) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.no_telp = no_telp;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(int no_telp) {
        this.no_telp = no_telp;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }

    public int getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(int tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }






}
