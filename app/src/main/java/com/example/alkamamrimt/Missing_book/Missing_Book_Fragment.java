package com.example.alkamamrimt.Missing_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.alkamamrimt.Books.Book_Adapter;
import com.example.alkamamrimt.Missing_book.Fragment_Adapter;
import com.example.alkamamrimt.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Missing_Book_Fragment extends AppCompatActivity {
    TabLayout tablout;
    TabItem missingbook, missingdetails;
    ViewPager viewPager;
    Fragment_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_book_fragment);

        tablout = findViewById(R.id.tabLayout);
        missingbook = findViewById(R.id.addmissingbook);
        missingdetails = findViewById(R.id.missingdetails);
        viewPager = findViewById(R.id.viewpager);


        adapter = new Fragment_Adapter(getSupportFragmentManager(), tablout.getTabCount());
        viewPager.setAdapter(adapter);

        tablout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1)
                {

                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablout));
    }


}
