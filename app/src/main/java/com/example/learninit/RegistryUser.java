package com.example.learninit;

import com.google.firebase.database.Exclude;

public class RegistryUser {
    @Exclude
    private String  user_id;
    private String felhasznaloNev;
    private String email;

    public RegistryUser() {
    }

    public RegistryUser(String user_id, String felhasznaloNev, String email) {
        this.user_id = user_id;
        this.felhasznaloNev = felhasznaloNev;
        this.email = email;
    }

    public RegistryUser(String felhasznaloNev, String email) {
        this.felhasznaloNev = felhasznaloNev;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }

    public void setFelhasznaloNev(String felhasznaloNev) {
        this.felhasznaloNev = felhasznaloNev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





}
