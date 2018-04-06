package org.vatalu.model.entity;

import java.sql.Timestamp;

public class Certificate {
    private Long id;
    private String number;
    private String email;
    private String address;
    private String studentId;
    private String isjudged;
    private Timestamp date;

    public Certificate(Long id, String number, String email, String address, String studentId, String isjudged) {
        this.id = id;
        this.number = number;
        this.email = email;
        this.address = address;
        this.studentId = studentId;
        this.isjudged = isjudged;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getIsjudged() {
        return isjudged;
    }

    public void setIsjudged(String isjudged) {
        this.isjudged = isjudged;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", studentId='" + studentId + '\'' +
                ", isjudged='" + isjudged + '\'' +
                ", date=" + date +
                '}';
    }
}
