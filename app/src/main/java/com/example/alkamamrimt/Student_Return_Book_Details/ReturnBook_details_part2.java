package com.example.alkamamrimt.Student_Return_Book_Details;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ReturnBook_details_part2 extends AppCompatActivity {
    RecyclerView recview;

    ArrayList<ReturnBook_details_part2_Model> datalist;
    ReturnBook_details_part2_Adapter adapter;
    ProgressBar progressBar;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.returnbook_details_main_part2);
        setTitle("Return Book Details");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recview=findViewById(R.id.recview);
        recview.setVisibility(View.GONE);
        recview.setLayoutManager(new LinearLayoutManager(this));

        datalist=new ArrayList<>();

        String course=getIntent().getStringExtra("student_course");
        String year=getIntent().getStringExtra("student_year");

        Conn conn = new Conn(this);

        Cursor cursor = conn.get_student_return_details(course, year);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ReturnBook_details_part2_Model obj = new ReturnBook_details_part2_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9) );
                datalist.add(obj);

                progressBar.setVisibility(View.GONE);
                recview.setVisibility(View.VISIBLE);
            }
            adapter = new ReturnBook_details_part2_Adapter(datalist);
            recview.setAdapter(adapter);


        } else {
            Toasty.info(this, "There's no return books in this class ", Toasty.LENGTH_SHORT).show();

            progressBar.setVisibility(View.GONE);
            recview.setVisibility(View.GONE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        MenuItem item = menu.findItem(R.id.searchbooks);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Student Name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
