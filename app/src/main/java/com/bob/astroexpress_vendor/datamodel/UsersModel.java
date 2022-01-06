package com.bob.astroexpress_vendor.datamodel;

public class UsersModel {
    String name,email, uid, dob, tob, pob,gender;

    public UsersModel() {
    }

    public UsersModel(String name, String uid, String dob, String tob, String pob, String gender) {
        this.name = name;
        this.uid = uid;
        this.dob = dob;
        this.tob = tob;
        this.pob = pob;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTob() {
        return tob;
    }

    public void setTob(String tob) {
        this.tob = tob;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }
}
