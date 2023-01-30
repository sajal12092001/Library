package com.example.alkamamrimt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.security.identity.CipherSuiteNotSupportedException;

import androidx.annotation.Nullable;

public class Conn extends SQLiteOpenHelper {
    private static final String dbname = "database.db";

    public Conn(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE books (\n" +
                "    id          INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    bookid      STRING  NOT NULL,\n" +
                "    name        STRING  NOT NULL,\n" +
                "    publication STRING  NOT NULL,\n" +
                "    author      STRING  NOT NULL,\n" +
                "    pages       STRING  NOT NULL,\n" +
                "    price       STRING  NOT NULL\n" +
                ");\n");

        sqLiteDatabase.execSQL("CREATE TABLE students (\n" +
                "    id         INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    name       STRING  NOT NULL,\n" +
                "    fathername STRING  NOT NULL,\n" +
                "    phone      STRING  NOT NULL,\n" +
                "    course     STRING  NOT NULL,\n" +
                "    year       STRING  NOT NULL\n" +
                ");\n");

        sqLiteDatabase.execSQL("CREATE TABLE missing_books (\n" +
                "    id          INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    bookid      STRING  NOT NULL,\n" +
                "    name        STRING  NOT NULL,\n" +
                "    publication STRING  NOT NULL,\n" +
                "    author      STRING  NOT NULL,\n" +
                "    price       STRING  NOT NULL\n" +
                ");\n");

        sqLiteDatabase.execSQL("CREATE TABLE Issue_Books (\n" +
                "    id        INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    bookid    STRING  NOT NULL,\n" +
                "    name      STRING  NOT NULL,\n" +
                "    fathername STRING  NOT NULL,\n" +
                "    phone     STRING  NOT NULL,\n" +
                "    course    STRING  NOT NULL,\n" +
                "    year      STRING  NOT NULL,\n" +
                "    issuedate STRING  NOT NULL\n" +
                ");\n");

        sqLiteDatabase.execSQL("CREATE TABLE Return_Books (\n" +
                "    id         INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    bookid     STRING  NOT NULL,\n" +
                "    name       STRING  NOT NULL,\n" +
                "    fathername STRING  NOT NULL,\n" +
                "    phone      STRING  NOT NULL,\n" +
                "    course     STRING  NOT NULL,\n" +
                "    year       STRING  NOT NULL,\n" +
                "    issuedate  STRING  NOT NULL,\n" +
                "    returndate STRING  NOT NULL,\n" +
                "    fine       STRING  NOT NULL\n" +
                ");\n");


        sqLiteDatabase.execSQL("CREATE TABLE Teacher_Issue_Books (\n" +
                "    id        INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    bookid    STRING  NOT NULL,\n" +
                "    name      STRING  NOT NULL,\n" +
                "    issuedate STRING  NOT NULL\n" +
                ");\n");

        sqLiteDatabase.execSQL("CREATE TABLE Teacher_Return_Books (\n" +
                "    id         INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    bookid     STRING  NOT NULL,\n" +
                "    name       STRING  NOT NULL,\n" +
                "    issuedate  STRING  NOT NULL,\n" +
                "    returndate       STRING  NOT NULL\n" +
                ");\n");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean add_book_details(String bookid, String bookname, String bookpublication, String bookauthor, String bookpages, String bookprice) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues books = new ContentValues();
        books.put("bookid", bookid);
        books.put("name", bookname);
        books.put("author", bookauthor);
        books.put("publication", bookpublication);
        books.put("pages", bookpages);
        books.put("price", bookprice);
        long r = db.insert("books", null, books);

        return r != -1;
    }

    public Cursor get_book_details() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select* from books", null);
    }

    public boolean add_student_details(String name, String fathername, String phone, String course, String year) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues books = new ContentValues();
        books.put("name", name);
        books.put("fathername", fathername);
        books.put("phone", phone);
        books.put("course", course);
        books.put("year", year);

        long r = db.insert("students", null, books);

        return r != -1;
    }

    public boolean add_missing_book_details(String bookid, String bookname, String bookpublication, String bookauthor, String bookprice) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues books = new ContentValues();
        books.put("bookid", bookid);
        books.put("name", bookname);
        books.put("author", bookauthor);
        books.put("publication", bookpublication);
        books.put("price", bookprice);
        long r = db.insert("missing_books", null, books);

        return r != -1;
    }


    public Cursor get_missing_book_details() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select * from missing_books", null);
    }


    public Cursor student_issue_details(String course, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select * from students where course='" + course + "'and year='" + year + "'", null);

    }

    public boolean add_issue_books(String bookid, String name, String fathername, String phone, String course, String year, String issuedate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("bookid", bookid);
        c.put("name", name);
        c.put("fathername", fathername);
        c.put("phone", phone);
        c.put("course", course);
        c.put("year", year);
        c.put("issuedate", issuedate);
        long r = db.insert("Issue_Books", null, c);

        return r != -1;

    }

    public Cursor get_student_issue_details(String course, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select * from Issue_Books where course='" + course + "'and year='" + year + "'", null);

    }

    public boolean add_return_book_details(String bookid, String name, String fathername, String phone, String course, String year, String issuedate, String returndate, String fine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues map = new ContentValues();
        map.put("bookid", bookid);
        map.put("name", name);
        map.put("fathername", fathername);
        map.put("phone", phone);
        map.put("course", course);
        map.put("year", year);
        map.put("issuedate", issuedate);
        map.put("returndate", returndate);
        map.put("fine", fine);
        long r = db.insert("Return_Books", null, map);

        return r != -1;

    }

    public void delete_issue_books_details(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.rawQuery("delete from Issue_Books where name='"+name+"'and phone='"+phone+"'",null);
        db.delete("Issue_Books", "name=? and phone=?", new String[]{name, phone});
    }


    public Cursor get_particular_book_details(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select* from books where bookid='" + id + "'", null);
    }

    public boolean add_teacher_issue_book(String id, String name, String issuedate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("bookid", id);
        c.put("name", name);
        c.put("issuedate", issuedate);
        long r = db.insert("Teacher_Issue_Books", null, c);
        return r != -1;
    }


    public Cursor get_Teacher_Issue_Book_Details() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select* from Teacher_Issue_Books", null);
    }


    public Cursor get_student_return_details(String course, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select * from Return_Books where course='" + course + "'and year='" + year + "'", null);

    }

    public boolean add_Teacher_return_book_details(String bookid, String name, String issuedate, String returndate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues map = new ContentValues();
        map.put("bookid", bookid);
        map.put("name", name);
        map.put("issuedate", issuedate);
        map.put("returndate", returndate);
        long r = db.insert("Teacher_Return_Books", null, map);

        return r != -1;

    }

    public void delete_teacher_issue_books_details(String name,String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.rawQuery("delete from Issue_Books where name='"+name+"'and phone='"+phone+"'",null);
        db.delete("Teacher_Issue_Books", "name=? and bookid=?", new String[]{name, id});
    }

    public Cursor get_Teacher_return_details() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("select * from Teacher_Return_Books", null);


    }
    public void delete_book_details(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("books", "bookid=?", new String[]{ id});
    }

}
