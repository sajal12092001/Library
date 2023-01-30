package com.example.alkamamrimt.Student_Return_Book;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class ReturnBook_details_part3 extends AppCompatActivity {
    DatePickerDialog pickerdate;
    EditText bookid, name, phone, course, year, issuedate, returndate, fine;
    Button missingbutton, returnbutton;



    @SuppressLint({"SetTextI18n", "Recycle"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_book_details_part3);
        setTitle("Students Return Book Details");


        bookid = findViewById(R.id.bookid);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        course = findViewById(R.id.course);
        year = findViewById(R.id.year);
        issuedate = findViewById(R.id.issuedate);
        returndate = findViewById(R.id.returndate);
        fine = findViewById(R.id.fine);

        missingbutton = findViewById(R.id.missingbutton);
        returnbutton = findViewById(R.id.returnbutton);

        String sbookid = getIntent().getStringExtra("bookid");
        String sname = getIntent().getStringExtra("name");
        String sfathername = getIntent().getStringExtra("fathername");
        String sphone = getIntent().getStringExtra("phone");
        String scourse = getIntent().getStringExtra("course");
        String ssyear = getIntent().getStringExtra("year");
        String sissuedate = getIntent().getStringExtra("issuedate");

        bookid.setText(sbookid);
        name.setText(sname);
        phone.setText(sphone);
        course.setText(scourse);
        year.setText(ssyear);
        issuedate.setText(sissuedate);

        returndate.setInputType(InputType.TYPE_NULL);

        returndate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                pickerdate = new DatePickerDialog(ReturnBook_details_part3.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view1, int year1, int monthOfYear, int dayOfMonth) {
                                returndate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);

                                @SuppressLint("SimpleDateFormat")
                                SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

                                Date date1 = null;
                                try {
                                    date1 = dates.parse(sissuedate);
                                    Date date2 = dates.parse(returndate.getText().toString().trim());
                                    //Comparing dates
                                    long difference = Math.abs(Objects.requireNonNull(date1).getTime() - Objects.requireNonNull(date2).getTime());
                                    long differenceDates = difference / (24 * 60 * 60 * 1000);

                                    if (differenceDates <= 7) {
                                        fine.setText("Rs.0");
                                    } else {
                                        fine.setText("Rs." + ((differenceDates - 7) * 2));
                                    }


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, year, month, day);
                pickerdate.show();


            }
        });

        returnbutton.setOnClickListener(view -> {
            Conn conn = new Conn(this);
            boolean check = conn.add_return_book_details(sbookid, sname, sfathername, sphone, scourse, ssyear, sissuedate, returndate.getText().toString().trim(), fine.getText().toString().trim());
            if (check) {
                Toasty.success(this, sname + " has return book successfully", Toasty.LENGTH_SHORT).show();

                bookid.setText("");
                name.setText("");
                phone.setText("");
                course.setText("");
                year.setText("");
                issuedate.setText("");
                returndate.setText("");
                fine.setText("");
                conn.delete_issue_books_details(sname, sphone);


            }


        });

        missingbutton.setOnClickListener(view -> {
            Conn conn = new Conn(this);
            conn.delete_issue_books_details(sname, sphone);

            Cursor cursor = conn.get_particular_book_details(sbookid);
            cursor.moveToFirst();

         boolean check=  conn.add_missing_book_details(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6));

         if (check) {
             Toasty.success(this,"This book has sent to missing book details",Toasty.LENGTH_SHORT).show();
             bookid.setText("");
             name.setText("");
             phone.setText("");
             course.setText("");
             year.setText("");
             issuedate.setText("");
             returndate.setText("");
             fine.setText("");
         }
         conn.delete_book_details(sbookid);

        });


    }


}


