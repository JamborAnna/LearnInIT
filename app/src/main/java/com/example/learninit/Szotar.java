package com.example.learninit;

public class Szotar {
    public String szo_id;
    public String magyar;
    public String angol;
    public String kep_id;
    public boolean tanult;

    public Szotar() {
    }



    public Szotar(String szo_id, String magyar, String angol, String kep_id, boolean tanult) {
        this.szo_id = szo_id;
        this.magyar = magyar;
        this.angol = angol;
        this.kep_id = kep_id;
        this.tanult = tanult;
    }

    public String getSzo_id() {
        return szo_id;
    }

    public void setSzo_id(String szo_id) {
        this.szo_id = szo_id;
    }

    public String getMagyar() {
        return magyar;
    }

    public void setMagyar(String magyar) {
        this.magyar = magyar;
    }

    public String getAngol() {
        return angol;
    }

    public void setAngol(String angol) {
        this.angol = angol;
    }

    public String getKep_id() {
        return kep_id;
    }

    public void setKep_id(String kep_id) {
        this.kep_id = kep_id;
    }

    public boolean isTanult() {
        return tanult;
    }

    public void setTanult(boolean tanult) {
        this.tanult = tanult;
    }
}
