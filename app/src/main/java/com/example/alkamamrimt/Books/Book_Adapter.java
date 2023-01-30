package com.example.alkamamrimt.Books;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.alkamamrimt.Books.Fragments.Add_Book.AddBookFragment;
import com.example.alkamamrimt.Books.Fragments.Books_Details.BookDetailsFragment;

public class Book_Adapter extends FragmentPagerAdapter {

    int tabcount;
    public Book_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {

            case 0:return new AddBookFragment();
            case 1:return new BookDetailsFragment();
            default:return null;
        }


    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
