package com.example.alkamamrimt.Teacher_Issue_Book;

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

public class Teacher_Issuebook_part1 extends AppCompatActivity {
    DatePickerDialog pickerdate;
    EditText bookid, issuedate, tname;
    Button isseubutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_issuebook_part1);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bookid = findViewById(R.id.bookid);
        tname = findViewById(R.id.name);
        issuedate = findViewById(R.id.issuedate);

        isseubutton = findViewById(R.id.issuebutton);
        issuedate.setInputType(InputType.TYPE_NULL);





        issuedate.setOnClickListener(view -> {

            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            pickerdate = new DatePickerDialog(Teacher_Issuebook_part1.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePicker view1, int year1, int monthOfYear, int dayOfMonth) {
                            issuedate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        }
                    }, year, month, day);
            pickerdate.show();
        });
        
        isseubutton.setOnClickListener(view -> {
            Conn conn=new Conn(this);
            String id=bookid.getText().toString().trim();
            String name=tname.getText().toString().trim();
            String tissuedate=issuedate.getText().toString().trim();
            boolean check=conn.add_teacher_issue_book(id,name,tissuedate);
            if (check) {
                Toasty.success(this,name+" has issued successfully",Toasty.LENGTH_SHORT).show();
                bookid.setText("");
                tname.setText("");
                issuedate.setText("");
            }
            ;
        });

    }
}