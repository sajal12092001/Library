package com.example.alkamamrimt.Missing_book.Fragments.Missing_Book_Details;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Missing_Details_Books#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Missing_Details_Books extends Fragment {
    RecyclerView recview;
    ArrayList<Missing_book_details_Model> datalist;
    Missing_book_details_Adapter adapter;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Missing_Details_Books() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Missing_Details_Books.
     */
    // TODO: Rename and change types and number of parameters
    public static Missing_Details_Books newInstance(String param1, String param2) {
        Missing_Details_Books fragment = new Missing_Details_Books();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_missing__details__books, container, false);


        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recview = v.findViewById(R.id.recview);
        recview.setVisibility(View.GONE);

        swipeRefreshLayout=v.findViewById(R.id.swiperefresh);

        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        datalist = new ArrayList<>();

        Conn conn = new Conn(getContext());
        Cursor cursor = conn.get_missing_book_details();
        while (cursor.moveToNext()) {
            Missing_book_details_Model obj = new Missing_book_details_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            datalist.add(obj);
        }
        adapter = new Missing_book_details_Adapter(datalist);
        recview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);
        recview.setVisibility(View.VISIBLE);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Cursor cursor = conn.get_missing_book_details();
                datalist.clear();
                while (cursor.moveToNext()) {
                    Missing_book_details_Model obj = new Missing_book_details_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                    datalist.add(obj);
                }
                adapter = new Missing_book_details_Adapter(datalist);
                recview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
                recview.setVisibility(View.VISIBLE);

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        inflater.inflate(R.menu.search_bar, menu);
        MenuItem item = menu.findItem(R.id.searchbooks);
        //  item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search book by id");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // Here is where we are going to implement the filter logic
                adapter.getFilter().filter(s);
                return true;
            }

        });


    }
}