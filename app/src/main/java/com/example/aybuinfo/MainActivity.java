package com.example.aybuinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
        tab_view_toolbar = findViewById(R.id.tab_view_toolbar);
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

        setFindContent("Aylık Yemek Menüsü");
        setWebSiteUrl("https://aybu.edu.tr/sks/");

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
    public void getWebSite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect(webSiteUrl).get();

                    String title = doc.title();

                    builder.append(title).append("\n");

                    if(findContent.equalsIgnoreCase("caContent")){

                        Elements links = doc.select("div.caContent a[href]");
                        log("Here : ", "AYBU Duyurular");

                        for(Element link : links) {
                            builder.append("\n").append(link.attr("href"))
                                    .append("\n").append(link.text());
                        }

                    } else if (findContent.equalsIgnoreCase("cnContent")) {
                        Elements links = doc.select("div.cnContent a[href]");
                        log("Here : ", "AYBU Haberler");
                        for(Element link : links) {
                            builder.append("\n").append(link.attr("href"))
                                    .append("\n").append(link.text());
                        }
                    } else {

                        Elements links = doc.select("a[href]");
                        log("Here : ", "Yemek Menu");
                        for(Element link : links) {
                            if(findContent.equalsIgnoreCase(link.text())) {
                                builder.append("\n").append(link.attr("href"))
                                        .append("\n").append(link.text());
                            }
                        }
                    }
                }catch (IOException e) {
                    builder.append("\n").append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] text = builder.toString().split("\n");
                        log("fragment_red_txt_title.setText(text[0]) : text[0]", text[0]);
                        fragment_red_txt_title.setText(text[0]);
                        fragment_red_j_soup_pnl_add.removeAllViews();
                        String textLink = "";
                        for (int i=2; i < text.length; i++) {
                            if(i%2 == 0) {
                                textLink = text[i].toString();
                            } else {
                                TextView textView = new TextView(context);
                                log("Text : ", text[i].toString());
                                SpannableString ss = new SpannableString(text[i]);

                                final String finalTextLimk = textLink;
                                ClickableSpan clickableSpan = new ClickableSpan() {
                                    @Override
                                    public void onClick(View widget) {
                                        Toast.makeText(context, finalTextLimk , Toast.LENGTH_LONG).show();
                                    }
                                };

                                ss.setSpan(clickableSpan, 0, text[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                textView.setText(ss);
                                textView.setMovementMethod(LinkMovementMethod.getInstance());
                                fragment_red_j_soup_pnl_add.addView(textView);
                            }
                        }
                    }
                });
            }
        }).start();
    }
    public void setWebSiteUrl(String siteUrl) {
        this.webSiteUrl = siteUrl;
    }
    public void setFindContent(String findContent) {
        this.findContent = findContent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private static void log(String msg, String vals) {
        System.out.println(String.format("\n" + msg + " " + vals));
    }
}
