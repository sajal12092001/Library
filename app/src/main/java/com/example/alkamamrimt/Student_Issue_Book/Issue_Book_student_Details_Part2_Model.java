package com.example.alkamamrimt.Student_Issue_Book;

public class Issue_Book_student_Details_Part2_Model {
    String name,fathername,phone,course,year;



    public Issue_Book_student_Details_Part2_Model(String name, String fathername, String phone, String course, String year) {
        this.name = name;
        this.fathername = fathername;
        this.phone = phone;
        this.course = course;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
