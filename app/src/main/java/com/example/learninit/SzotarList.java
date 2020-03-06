package com.example.learninit;

class SzotarList {

    private String magyar;
    private String angol;

    public SzotarList() {
    }

    public SzotarList(String magyar, String angol) {
        this.magyar = magyar;
        this.angol = angol;
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
}
