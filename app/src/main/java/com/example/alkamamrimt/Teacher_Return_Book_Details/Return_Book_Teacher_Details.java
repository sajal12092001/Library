package com.example.alkamamrimt.Teacher_Return_Book_Details;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import java.util.ArrayList;

public class Return_Book_Teacher_Details extends AppCompatActivity {

    RecyclerView recview;
    ProgressBar progressBar;
    ArrayList<Return_Book_Teacher_Details_Model> datalist;
    Return_Book_Teacher_Details_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book_teacher_details2_list);
        setTitle("Teacher's Return Book Details");

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        recview.setVisibility(View.INVISIBLE);

        datalist = new ArrayList<>();

        Conn conn=new Conn(this);
        Cursor cursor=conn.get_Teacher_return_details();
        while (cursor.moveToNext())
        {
            Return_Book_Teacher_Details_Model obj=new Return_Book_Teacher_Details_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            datalist.add(obj);
        }
        adapter=new Return_Book_Teacher_Details_Adapter(datalist);
        recview.setAdapter(adapter);
        recview.setVisibility(View.VISIBLE);


    }
}