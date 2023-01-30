package com.example.alkamamrimt.Add_Student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Add_Student extends AppCompatActivity {
    EditText name, fname, sphone;
    Spinner course, year;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        Objects.requireNonNull(getSupportActionBar()).hide();

        name = findViewById(R.id.studentname);
        fname = findViewById(R.id.studentfathername);
        sphone = findViewById(R.id.studentphone);
        course = findViewById(R.id.studentcourcename);
        year = findViewById(R.id.studentyear);
        add = findViewById(R.id.addbutton);


        add.setOnClickListener(view -> {


            String sname = name.getText().toString().trim().toUpperCase();
            String sfname = fname.getText().toString().trim().toUpperCase();
            String phone = sphone.getText().toString().trim();
            String scourse = course.getSelectedItem().toString().trim();
            String syear = year.getSelectedItem().toString().trim();

            if (sname.isEmpty()) {
                name.setError("Enter the student name");
            } else if (sfname.isEmpty()) {
                fname.setError("Enter the Father's Name");
            } else if (phone.isEmpty()) {
                sphone.setError("Enter the Student Phone no.");
            } else if (scourse.equalsIgnoreCase("-Select Cource-")) {
                ((TextView) course.getSelectedView()).setError("Select course");
            } else if (syear.equalsIgnoreCase("-Select Year-")) {
                ((TextView) year.getSelectedView()).setError("Select year");
            } else {
               Conn conn=new Conn(this);
               boolean check=conn.add_student_details(sname,sfname,phone,scourse,syear);
               if (check)
                   Toasty.success(this,"Successfully added",Toasty.LENGTH_SHORT).show();
               name.setText("");
               fname.setText("");
               sphone.setText("");
               course.setSelection(0);
               year.setSelection(0);

            }
        });




    }


}

