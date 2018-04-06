package org.vatalu.model.response;

public class CertificateResponse extends CommonResponse{
    private String number;
    private String email;
    private String address;
    private String studentId;
    private Boolean isjudged;

    public CertificateResponse(Boolean result) {
        super(result);
    }

    public CertificateResponse(Boolean result, String number, String email, String address, String studentId, Boolean isjudged) {
        super(result);
        this.number = number;
        this.email = email;
        this.address = address;
        this.studentId = studentId;
        this.isjudged = isjudged;
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

    public Boolean getIsjudged() {
        return isjudged;
    }

    public void setIsjudged(Boolean isjudged) {
        this.isjudged = isjudged;
    }
}
