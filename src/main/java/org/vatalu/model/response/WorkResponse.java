package org.vatalu.model.response;

import org.vatalu.model.entity.Work;

public class WorkResponse extends CommonResponse {
    private int year;
    private int month;
    private String area;
    private String level;
    private String school;
    private String number;
    private String workid;
    private String workname;
    private String student1;
    private String student1Id;
    private String student2;
    private String student2Id ;
    private String student3;
    private String student3Id;
    private String teacher1;
    private String teacher1Id;
    private String teacher2;
    private String teacher2Id;

    public WorkResponse(Boolean result) {
        super(result);
    }

    public WorkResponse(Boolean result, int year, int month, String area, String level, String school, String number, String workid, String workname, String student1, String student1Id, String student2, String student2Id, String student3, String student3Id, String teacher1, String teacher1Id, String teacher2, String teacher2Id) {
        super(result);
        this.year = year;
        this.month = month;
        this.area = area;
        this.level = level;
        this.school = school;
        this.number = number;
        this.workid = workid;
        this.workname = workname;
        this.student1 = student1;
        this.student1Id = student1Id;
        this.student2 = student2;
        this.student2Id = student2Id;
        this.student3 = student3;
        this.student3Id = student3Id;
        this.teacher1 = teacher1;
        this.teacher1Id = teacher1Id;
        this.teacher2 = teacher2;
        this.teacher2Id = teacher2Id;
    }

    public WorkResponse(Boolean result, Work work) {
        super(result);
        this.year = work.getYear();
        this.month = work.getMonth();
        this.area = work.getArea();
        this.level = work.getLevel();
        this.school = work.getSchool();
        this.number = work.getNumber();
        this.workid = work.getWorkid();
        this.workname = work.getWorkname();
        this.student1 = work.getStudent1();
        this.student1Id = work.getStudent1Id();
        this.student2 = work.getStudent2();
        this.student2Id = work.getTeacher2Id();
        this.student3 = work.getStudent3();
        this.student3Id = work.getStudent3Id();
        this.teacher1 = work.getTeacher1();
        this.teacher1Id = work.getTeacher1Id();
        this.teacher2 = work.getTeacher2();
        this.teacher2Id = work.getTeacher2Id();
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
}
