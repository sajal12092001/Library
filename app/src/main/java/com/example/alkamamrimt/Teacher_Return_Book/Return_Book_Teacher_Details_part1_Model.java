package com.example.alkamamrimt.Teacher_Return_Book;


public class Return_Book_Teacher_Details_part1_Model {
    String name,bookid,issuedate;



    public Return_Book_Teacher_Details_part1_Model(String bookid,String name, String issuedate) {
        this.name = name;
        this.bookid = bookid;
        this.issuedate = issuedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }
}
