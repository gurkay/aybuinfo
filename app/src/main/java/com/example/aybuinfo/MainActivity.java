package com.example.aybuinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    Toolbar tab_view_toolbar, tab_view_toolbar_tab;
    ViewPager tab_view_view_pager;
    TabLayout tab_view_tab_layout;

    PageAdapter pageAdapter;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }
    public void init() {
        tab_view_toolbar = findViewById(R.id.tab_view_toolbar);
        tab_view_toolbar_tab = findViewById(R.id.tab_view_toolbar_tab);
        tab_view_view_pager = findViewById(R.id.tab_view_view_pager);
        tab_view_tab_layout = findViewById(R.id.tab_view_tab_layout);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new RedFragment(), "Food List");
        pageAdapter.addFragment(new GreenFragment(), "Announcements");
        pageAdapter.addFragment(new BlueFragment(), "News");

        tab_view_view_pager.setAdapter(pageAdapter);

        tab_view_tab_layout.setupWithViewPager(tab_view_view_pager);

        tab_view_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        getWindow().setStatusBarColor(Color.RED);
                        tab_view_toolbar.setBackgroundColor(Color.RED);
                        tab_view_toolbar_tab.setBackgroundColor(Color.RED);
                        tab_view_tab_layout.setBackgroundColor(Color.RED);
                        break;

                    case 1:
                        getWindow().setStatusBarColor(Color.GREEN);
                        tab_view_toolbar.setBackgroundColor(Color.GREEN);
                        tab_view_toolbar_tab.setBackgroundColor(Color.GREEN);
                        tab_view_tab_layout.setBackgroundColor(Color.GREEN);
                        break;

                    case 2:
                        getWindow().setStatusBarColor(Color.BLUE);
                        tab_view_toolbar.setBackgroundColor(Color.BLUE);
                        tab_view_toolbar_tab.setBackgroundColor(Color.BLUE);
                        tab_view_tab_layout.setBackgroundColor(Color.BLUE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
