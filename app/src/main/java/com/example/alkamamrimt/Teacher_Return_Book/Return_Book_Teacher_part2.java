package com.example.alkamamrimt.Teacher_Return_Book;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class Return_Book_Teacher_part2 extends AppCompatActivity {
    DatePickerDialog pickerdate;
    EditText bookid, name, issuedate, returndate;
    Button missingbutton, returnbutton;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book_teacher_part2);

        setTitle("Teacher's Return Book Details");




        bookid = findViewById(R.id.bookid);
        name = findViewById(R.id.name);
        issuedate = findViewById(R.id.issuedate);
        returndate = findViewById(R.id.returndate);

        missingbutton = findViewById(R.id.missingbutton);
        returnbutton = findViewById(R.id.returnbutton);

        String tbookid = getIntent().getStringExtra("bookid");
        String tname = getIntent().getStringExtra("name");
        String tissuedate = getIntent().getStringExtra("issuedate");

        bookid.setText(tbookid);
        name.setText(tname);
        issuedate.setText(tissuedate);

        returndate.setInputType(InputType.TYPE_NULL);

        returndate.setOnClickListener(view -> {

            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            pickerdate = new DatePickerDialog(Return_Book_Teacher_part2.this,
                    (view1, year1, monthOfYear, dayOfMonth) ->
                            returndate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            pickerdate.show();
        });


        returnbutton.setOnClickListener(view -> {
Conn conn=new Conn(this);
boolean check=conn.add_Teacher_return_book_details(tbookid,tname,tissuedate,returndate.getText().toString().trim());
if (check) {
    Toasty.success(this, tname + " has return book successfully", Toasty.LENGTH_SHORT).show();
    bookid.setText("");
    name.setText("");
    issuedate.setText("");
    returndate.setText("");
    conn.delete_teacher_issue_books_details(tname,tbookid);

}


        });


        missingbutton.setOnClickListener(view -> {
            Conn conn = new Conn(this);
            conn.delete_teacher_issue_books_details(tname,tbookid);

            Cursor cursor = conn.get_particular_book_details(tbookid);
            cursor.moveToFirst();

            boolean check=  conn.add_missing_book_details(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6));

            if (check) {
                Toasty.success(this,"This book has sent to missing book details",Toasty.LENGTH_SHORT).show();
                bookid.setText("");
                name.setText("");
                issuedate.setText("");
                returndate.setText("");
            }
            conn.delete_book_details(tbookid);

        });
    }
}