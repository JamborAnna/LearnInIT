package com.example.learninit.ui;

public class TanultSzavak {
    private String user_id;
    private  String tanultMagyar;
    private  String tanultAngol;


    public TanultSzavak(String magyar, String angol) {
    }

    public TanultSzavak() {
        this.user_id = user_id;
        this.tanultMagyar = tanultMagyar;
        this.tanultAngol = tanultAngol;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTanultMagyar() {
        return tanultMagyar;
    }

    public void setTanultMagyar(String tanultMagyar) {
        this.tanultMagyar = tanultMagyar;
    }

    public String getTanultAngol() {
        return tanultAngol;
    }

    public void setTanultAngol(String tanultAngol) {
        this.tanultAngol = tanultAngol;
    }
}
