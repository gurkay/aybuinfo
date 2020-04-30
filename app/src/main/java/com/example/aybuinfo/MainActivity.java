package com.example.aybuinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;

import android.view.Menu;

import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    // Tab Menu Setting
    Toolbar tab_view_toolbar, tab_view_toolbar_tab;
    ViewPager tab_view_view_pager;
    TabLayout tab_view_tab_layout;
    PageAdapter pageAdapter;

    // JSoup Librariy Setting
    TextView fragment_red_txt_title;
    LinearLayout fragment_red_j_soup_pnl_add;

    private String webSiteUrl;
    public String findContent;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }
    public void init() {

       // tab_view_toolbar = findViewById(R.id.tab_view_toolbar);
        tab_view_toolbar_tab = findViewById(R.id.tab_view_toolbar_tab);
        tab_view_view_pager = findViewById(R.id.tab_view_view_pager);
        tab_view_tab_layout = findViewById(R.id.tab_view_tab_layout);

        fragment_red_txt_title = findViewById(R.id.fragment_red_txt_title);
        fragment_red_j_soup_pnl_add = findViewById(R.id.fragment_red_j_soup_pnl_add);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new RedFragment(), "Food List");
        pageAdapter.addFragment(new GreenFragment(), "Announce");
        pageAdapter.addFragment(new BlueFragment(), "News");

        tab_view_view_pager.setAdapter(pageAdapter);
        tab_view_tab_layout.setupWithViewPager(tab_view_view_pager);

        tab_view_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        getWindow().setStatusBarColor(Color.rgb(205,92,92));
                       // tab_view_toolbar.setBackgroundColor(Color.rgb(205,92,92));
                        tab_view_toolbar_tab.setBackgroundColor(Color.rgb(205,92,92));
                        tab_view_tab_layout.setBackgroundColor(Color.rgb(205,92,92));

                        break;

                    case 1:
                        getWindow().setStatusBarColor(Color.rgb(205,133,63));
                      //  tab_view_toolbar.setBackgroundColor(Color.rgb(205,133,63));
                        tab_view_toolbar_tab.setBackgroundColor(Color.rgb(205,133,63));
                        tab_view_tab_layout.setBackgroundColor(Color.rgb(205,133,63));
                        break;

                    case 2:
                        getWindow().setStatusBarColor(Color.rgb(219,112,147));
                     //   tab_view_toolbar.setBackgroundColor(Color.rgb(219,112,147));
                        tab_view_toolbar_tab.setBackgroundColor(Color.rgb(219,112,147));
                        tab_view_tab_layout.setBackgroundColor(Color.rgb(219,112,147));
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

    private static void log(String msg, String vals) {
        System.out.println(String.format("\n" + msg + " " + vals));
    }
}
