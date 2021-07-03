package com.kmitl.web_programming_2_63_final.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "FixDB")
public class InformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String date;
    private String objectfail;
    private String objectcondition;
    private String place;
    private String docName;
    private String docType;
    private String status ;
    @Lob
    private byte[] picture;

    public InformationEntity() {

    }

    public InformationEntity(String docName, String docType, byte[] picture) {
        this.docName = docName;
        this.docType = docType;
        this.picture = picture;
    }

    public InformationEntity(Integer id, String firstname, String lastname, String phone, String email, String date, String objectfail, String status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.objectfail = objectfail;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
