package com.example.alkamamrimt.Missing_book.Fragments.Add_Book_Missing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.alkamamrimt.Conn;
import com.example.alkamamrimt.R;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_Missing_Book_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Missing_Book_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_Missing_Book_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Missing_Book_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_Missing_Book_Fragment newInstance(String param1, String param2) {
        Add_Missing_Book_Fragment fragment = new Add_Missing_Book_Fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EditText id, name, publication, author, price;
        Button add;

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add__missing__book_, container, false);
        id = v.findViewById(R.id.bookid);
        name = v.findViewById(R.id.bookname);
        author = v.findViewById(R.id.bookauthor);
        publication = v.findViewById(R.id.bookpublication);
        price = v.findViewById(R.id.bookprice);
        add = v.findViewById(R.id.addbutton);

        add.setOnClickListener(view -> {
            String bookid = id.getText().toString().trim();
            String bookname = name.getText().toString().trim().toUpperCase();
            String bookpublication = publication.getText().toString().trim().toUpperCase();
            String bookauthor = author.getText().toString().trim().toUpperCase();
            String bookprice = price.getText().toString().trim().toUpperCase();

            if (bookid.isEmpty() && bookname.isEmpty() && bookpublication.isEmpty() && bookauthor.isEmpty() && bookprice.isEmpty()) {
                id.setError("This field is required");
                name.setError("This field is required");
                publication.setError("This field is required");
                author.setError("This field is required");
                price.setError("This field is required");
            } else if (bookid.isEmpty()) {
                id.setError("This field is required");
            } else if (bookname.isEmpty()) {
                name.setError("This field is required");

            } else if (bookpublication.isEmpty()) {
                publication.setError("This field is required");

            } else if (bookauthor.isEmpty()) {
                author.setError("This field is required");

            } else if (bookprice.isEmpty()) {
                price.setError("This field is required");

            } else {

                Conn conn=new Conn(getContext());
                boolean check=conn.add_missing_book_details(bookid,bookname,bookpublication,bookauthor,bookprice);
                if (check)
                    Toasty.success(getContext(),"Successfully added to missing book details",Toasty.LENGTH_SHORT).show();

                id.setText("");
                name.setText("");
                publication.setText("");
                author.setText("");
                price.setText("");

            }

        });



        return v;
    }
}