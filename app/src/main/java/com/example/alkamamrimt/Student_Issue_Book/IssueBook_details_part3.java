package com.example.alkamamrimt.Student_Issue_Book;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;


import java.util.Calendar;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class IssueBook_details_part3 extends AppCompatActivity {

    DatePickerDialog pickerdate;
    EditText bookid, issuedate, sname, sphone, scourse, ssyear;
    Button isseubutton;


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_book_details_part3);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bookid = findViewById(R.id.bookid);
        sname = findViewById(R.id.name);
        sphone = findViewById(R.id.phone);
        scourse = findViewById(R.id.course);
        ssyear = findViewById(R.id.year);
        issuedate = findViewById(R.id.issuedate);


        String name = getIntent().getStringExtra("name");
        String fathername = getIntent().getStringExtra("fathername");
        String phone = getIntent().getStringExtra("phone");
        String course = getIntent().getStringExtra("course");
        String syear = getIntent().getStringExtra("year");

        sname.setText(name);
        sphone.setText(phone);
        scourse.setText(course);
        ssyear.setText(syear);


        issuedate.setInputType(InputType.TYPE_NULL);

        isseubutton = findViewById(R.id.issuebutton);



        issuedate.setOnClickListener(view -> {


            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            pickerdate = new DatePickerDialog(IssueBook_details_part3.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year1, int monthOfYear, int dayOfMonth) {
                            issuedate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        }
                    }, year, month, day);
            pickerdate.show();
        });


        isseubutton.setOnClickListener(view -> {

            String getbookid = bookid.getText().toString().trim();
            String getissuedate = issuedate.getText().toString().trim();


            if (getbookid.isEmpty() && name.isEmpty() && fathername.isEmpty() && phone.isEmpty() && course.isEmpty() && syear.isEmpty() && getissuedate.isEmpty()) {
                bookid.setError("This field is required");
                sname.setError("This field is required");
                sphone.setError("This field is required");
                scourse.setError("This field is required");
                ssyear.setError("This field is required");
                issuedate.setError("This field is required");

            } else if (name.isEmpty()) {
                sname.setError("This field is required");
            } else if (phone.isEmpty()) {
                sphone.setError("This field is required");

            } else if (course.isEmpty()) {
                scourse.setError("This field is required");

            } else if (syear.isEmpty()) {
                ssyear.setError("This field is required");

            } else if (getissuedate.isEmpty()) {
                issuedate.setError("This field is required");

            } else if (getbookid.isEmpty()) {
                bookid.setError("This field is required");

            } else {
                Conn conn = new Conn(this);
                boolean check = conn.add_issue_books(getbookid, name,fathername, phone, course, syear, getissuedate);
                if (check) {
                    Toasty.success(this, name + " has issued book successfully", Toasty.LENGTH_SHORT).show();
                    bookid.setText("");
                    sname.setText("");
                    sphone.setText("");
                    scourse.setText("");
                    ssyear.setText("");
                    issuedate.setText("");
                }
            }

        });

    }
}






