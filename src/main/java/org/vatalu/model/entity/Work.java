package org.vatalu.model.entity;

public class Work {
    private Long id;
    private int year = 2000;
    private int month = 1;
    private String area = "";
    private String level = "";
    private String school = "";
    private String number = "";
    private String workid = "";
    private String workname = "";
    private String student1 = "";
    private String student1Id = "";
    private String student2 = "";
    private String student2Id = "";
    private String student3 = "";
    private String student3Id;
    private String teacher1 = "";
    private String teacher1Id = "";
    private String teacher2 = "";
    private String teacher2Id = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getStudent1() {
        return student1;
    }

    public void setStudent1(String student1) {
        this.student1 = student1;
    }

    public String getStudent1Id() {
        return student1Id;
    }

    public void setStudent1Id(String student1Id) {
        this.student1Id = student1Id;
    }

    public String getStudent2() {
        return student2;
    }

    public void setStudent2(String student2) {
        this.student2 = student2;
    }

    public String getStudent2Id() {
        return student2Id;
    }

    public void setStudent2Id(String student2Id) {
        this.student2Id = student2Id;
    }

    public String getStudent3() {
        return student3;
    }

    public void setStudent3(String student3) {
        this.student3 = student3;
    }

    public String getStudent3Id() {
        return student3Id;
    }

    public void setStudent3Id(String student3Id) {
        this.student3Id = student3Id;
    }

    public String getTeacher1() {
        return teacher1;
    }

    public void setTeacher1(String teacher1) {
        this.teacher1 = teacher1;
    }

    public String getTeacher1Id() {
        return teacher1Id;
    }

    public void setTeacher1Id(String teacher1Id) {
        this.teacher1Id = teacher1Id;
    }

    public String getTeacher2() {
        return teacher2;
    }

    public void setTeacher2(String teacher2) {
        this.teacher2 = teacher2;
    }

    public String getTeacher2Id() {
        return teacher2Id;
    }

    public void setTeacher2Id(String teacher2Id) {
        this.teacher2Id = teacher2Id;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", area='" + area + '\'' +
                ", level='" + level + '\'' +
                ", school='" + school + '\'' +
                ", number='" + number + '\'' +
                ", workid='" + workid + '\'' +
                ", workname='" + workname + '\'' +
                ", student1='" + student1 + '\'' +
                ", student1Id='" + student1Id + '\'' +
                ", student2='" + student2 + '\'' +
                ", student2Id='" + student2Id + '\'' +
                ", student3='" + student3 + '\'' +
                ", student3Id='" + student3Id + '\'' +
                ", teacher1='" + teacher1 + '\'' +
                ", teacher1Id='" + teacher1Id + '\'' +
                ", teacher2='" + teacher2 + '\'' +
                ", teacher2Id='" + teacher2Id + '\'' +
                '}';
    }
}
