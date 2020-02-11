package com.example.learninit;

public class RegistryUser {
    public String  user_id;
    public String felhasznaloNev;
    public String email;
    public  Boolean tanult;



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

    public Boolean getTanult() {
        return tanult;
    }

    public void setTanult(Boolean tanult) {
        this.tanult = tanult;
    }




}
