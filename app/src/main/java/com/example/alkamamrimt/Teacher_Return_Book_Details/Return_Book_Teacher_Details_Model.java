package com.example.alkamamrimt.Teacher_Return_Book_Details;

public class Return_Book_Teacher_Details_Model {
    String bookid,name,issuedate,returndate;

    public Return_Book_Teacher_Details_Model() {
    }

    public Return_Book_Teacher_Details_Model(String bookid, String name, String issuedate, String returndate) {
        this.bookid = bookid;
        this.name = name;
        this.issuedate = issuedate;
        this.returndate = returndate;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }
}
