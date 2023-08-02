package com.sugadev.dietta.User;

public class Users {

    private int idUser;
    private String username;
    private String name;
    private int beratBadan;
    private int tinggiBadan;

    public Users() {
    }

    public Users(int idUser, String username, String name, int beratBadan, int tinggiBadan) {
        this.idUser = idUser;
        this.username = username;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
