package com.example.alkamamrimt.Missing_book.Fragments.Missing_Book_Details;

public class Missing_book_details_Model {

    String name;
    String publication;
    String  price;
    String author;
    String id;




    public Missing_book_details_Model(String id,String name, String publication, String author, String price) {
        this.name = name;
        this.publication = publication;
        this.price = price;
        this.author = author;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}


