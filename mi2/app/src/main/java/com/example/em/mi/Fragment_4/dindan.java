package com.example.em.mi.Fragment_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.em.mi.Fragment_4.scroll.NavitationScrollLayout;
import com.example.em.mi.Fragment_4.scroll.ViewPagerAdapter;
import com.example.em.mi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EM on 2017/4/18.
 */

public class dindan extends AppCompatActivity{
    private NavitationScrollLayout navitationScrollLayout;
    private ViewPager viewPager;
    private String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
    private ViewPagerAdapter viewPagerAdapter;
    private List<Fragment> fragments;
    private ImageView dindanbtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_four_dindan);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        dindanbtn= (ImageView) findViewById(R.id.dindanbtn);
        dindanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navitationScrollLayout = (NavitationScrollLayout) findViewById(R.id.bar);

        fragments =  new ArrayList<>();
        fragments.add(new all_orders());
        fragments.add(new obligation());
        fragments.add(new shipped());
        fragments.add(new receive());
        fragments.add(new evaluated());

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);

        navitationScrollLayout.setViewPager(this, titles, viewPager, R.color.black, R.color.colorAccent, 12, 12, 5, true, R.color.orangered, 0f, 15f, 15f, 70);
        navitationScrollLayout.setBgLine(this, 1, R.color.lightgray);
        navitationScrollLayout.setNavLine(this, 3, R.color.mediumseagreen);

                navitationScrollLayout.setOnTitleClickListener(new NavitationScrollLayout.OnTitleClickListener() {
                    @Override
                    public void onTitleClick(View v) {
                        Toast.makeText(dindan.this, ((TextView)v).getText(), Toast.LENGTH_SHORT).show();
                    }
                });

                navitationScrollLayout.setOnNaPageChangeListener(new NavitationScrollLayout.OnNaPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
