package com.example.em.mi.Fragment_4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.em.mi.R;

/**
 * Created by EM on 2017/4/19.
 */

public class aftermarket extends FragmentActivity{
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    int currentPage;
    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {after_sale.class, after_sale.class};
    private ImageView aftbtn;
    //Tab选项卡的文字
    private String mTextviewArray[] = {"全部", "待用户处理"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_four_aftermarket);
        aftbtn= (ImageView) findViewById(R.id.aftbtn);
        aftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }
    /**
     * 初始化组件
     */
    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.container_fragment);

        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(mTextviewArray[i]);
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.getTabWidget().setDividerDrawable(R.color.white);

        }
        mTabHost.setCurrentTab(currentPage);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.fragmenttab, null);
        TextView textView = (TextView) view.findViewById(R.id.textView7);
        textView.setText(mTextviewArray[index]);

        return view;
    }
}
