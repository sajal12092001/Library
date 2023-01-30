package com.example.alkamamrimt.Missing_book;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.alkamamrimt.Missing_book.Fragments.Add_Book_Missing.Add_Missing_Book_Fragment;
import com.example.alkamamrimt.Missing_book.Fragments.Missing_Book_Details.Missing_Details_Books;

public class Fragment_Adapter extends FragmentPagerAdapter {
    int count;

    public Fragment_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        count = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Add_Missing_Book_Fragment();
            case 1:
                return new Missing_Details_Books();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return count;
    }
}
