package com.example.alkamamrimt.Student_Return_Book_Details;

public class ReturnBook_details_part2_Model {
    String bookid, course, fathername, issuedate, name, phone, year, returndate, fine;

    public ReturnBook_details_part2_Model() {
    }

    public ReturnBook_details_part2_Model(String bookid, String name, String fathername, String phone, String course, String year, String issuedate, String returndate, String fine) {
        this.bookid = bookid;
        this.course = course;
        this.fathername = fathername;
        this.issuedate = issuedate;
        this.name = name;
        this.phone = phone;
        this.year = year;
        this.returndate = returndate;
        this.fine = fine;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}
