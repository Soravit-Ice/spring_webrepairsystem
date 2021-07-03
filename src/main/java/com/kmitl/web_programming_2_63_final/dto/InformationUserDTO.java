package com.kmitl.web_programming_2_63_final.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


public class InformationUserDTO {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String date;
    private String objectfail;
    private String objectcondition;
    private String place;
    private byte[] picture;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObjectfail() {
        return objectfail;
    }

    public void setObjectfail(String objectfail) {
        this.objectfail = objectfail;
    }

    public String getObjectcondition() {
        return objectcondition;
    }

    public void setObjectcondition(String objectcondition) {
        this.objectcondition = objectcondition;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
