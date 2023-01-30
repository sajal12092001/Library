package com.example.alkamamrimt.Student_Return_Book;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alkamamrimt.R;

import java.util.Objects;

public class ReturnBook_course_year_part1 extends AppCompatActivity {

    Spinner course,year;
    Button search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_book_course_year_part1);
        Objects.requireNonNull(getSupportActionBar()).hide();

        course=findViewById(R.id.studentcourcename);
        year=findViewById(R.id.studentcourceyear);

        search=findViewById(R.id.searchbutton);

        search.setOnClickListener(view -> {
            String getcourse=course.getSelectedItem().toString().trim();
            String getyear=year.getSelectedItem().toString().trim();
            Intent intent=new Intent(ReturnBook_course_year_part1.this, ReturnBook_student_details_part2.class);
            intent.putExtra("student_course",getcourse);
            intent.putExtra("student_year",getyear);
            startActivity(intent);
        });
    }
}
