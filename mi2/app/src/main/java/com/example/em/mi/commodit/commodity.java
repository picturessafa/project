package com.example.em.mi.commodit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.em.mi.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by EM on 2017/4/20.
 */

public class commodity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout frontView, bottomView;

    private FloatingActionButton fab;

    private AnimatorSet showAnim,hiddenAnim;

    private long fWidth,fHeight, bHeight;

    private TextView tvCloseBottom;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity);

      /*  Toolbar tb= (Toolbar) findViewById(R.id.tb_detail );//标题id
       tb.setNavigationIcon(R.mipmap.bg_back_black);//返回按钮*/


        fab= (FloatingActionButton) findViewById(R.id.fab);//添加
        fab.setOnClickListener(this);

        tvCloseBottom= (TextView) findViewById(R.id.tv_close_bottom);//关闭
        tvCloseBottom.setOnClickListener(this);

        ViewPager viewPager= (ViewPager) findViewById(R.id.view_pager_detail );
        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        initView();

    }

    private void initView() {
        frontView = (LinearLayout) findViewById(R.id.front);
        ViewTreeObserver vto= frontView.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fWidth = frontView.getMeasuredWidth();
                fHeight = frontView.getMeasuredHeight();

            }
        });
        bottomView = (LinearLayout) findViewById(R.id.bottom );
        ViewTreeObserver sVto= bottomView.getViewTreeObserver();
        sVto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bHeight = bottomView.getMeasuredHeight();
                initShowAnim();
                initHiddenAnim();
            }
        });

}

    private void initShowAnim(){
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(frontView,"scaleX",1.0f,0.8f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(frontView,"scaleY",1.0f,0.8f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(frontView,"alpha",1.0f,0.5f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(frontView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(frontView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(frontView,"translationY",0,-0.1f* fHeight);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(bottomView,"translationY", bHeight,0);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                bottomView.setVisibility(View.VISIBLE);
            }
        });
        showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
    }

    private void initHiddenAnim(){
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(frontView,"scaleX",0.8f,1.0f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(frontView,"scaleY",0.8f,1.0f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(frontView,"alpha",0.5f,1.0f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationAnim = ObjectAnimator.ofFloat(frontView, "rotationX",0f, 10f);
        fViewRotationAnim.setDuration(150);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(frontView, "rotationX",10f, 0f);
        fViewResumeAnim.setDuration(200);
        fViewResumeAnim.setStartDelay(150);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(frontView,"translationY",-fHeight *0.1f,0);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(bottomView,"translationY",0, bHeight);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                bottomView.setVisibility(View.INVISIBLE);
            }
        });
        hiddenAnim=new AnimatorSet();
        hiddenAnim.playTogether(fViewScaleXAnim, fViewAlphaAnim,fViewRotationAnim,fViewResumeAnim, fViewScaleYAnim,fViewTransYAnim, sViewTransYAnim);
        hiddenAnim.setDuration(350);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==fab.getId()){
            showAnim.start();
        }else if(v.getId()==tvCloseBottom.getId()){
            hiddenAnim.start();
        }

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter{


        private MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return FragmentOne.newInstance();
                case 1:return FragmentTwo.newInstance();
                case 2:return FragmentThree.newInstance();
                default:return FragmentOne.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:return "详情";
                case 1:return "评价";
                case 2:return "推荐";
                default:return "详情";
            }
        }
    }
}

