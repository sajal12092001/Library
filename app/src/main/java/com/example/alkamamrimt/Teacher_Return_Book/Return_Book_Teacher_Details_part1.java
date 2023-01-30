package com.example.alkamamrimt.Teacher_Return_Book;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import java.util.ArrayList;

public class Return_Book_Teacher_Details_part1 extends AppCompatActivity {
    RecyclerView recview;
    ArrayList<Return_Book_Teacher_Details_part1_Model> datalist;

    Return_Book_Teacher_Details_part1_Adapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book_teacher_details);

        setContentView(R.layout.return_book_student_details_part2);
        setTitle("Teacher Issue Book Details");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recview = findViewById(R.id.recview);
        recview.setVisibility(View.GONE);

        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();

        Conn conn=new Conn(this);
        Cursor cursor=conn.get_Teacher_Issue_Book_Details();
        while (cursor.moveToNext())
        {
            Return_Book_Teacher_Details_part1_Model obj=new Return_Book_Teacher_Details_part1_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            datalist.add(obj);
        }
        adapter=new Return_Book_Teacher_Details_part1_Adapter(datalist);
        recview.setAdapter(adapter);
        recview.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        MenuItem item = menu.findItem(R.id.searchbooks);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Teacher Name");
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