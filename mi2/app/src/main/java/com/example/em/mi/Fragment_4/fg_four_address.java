package com.example.em.mi.Fragment_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.em.mi.Fragment_1.Fragment_1;
import com.example.em.mi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EM on 2017/4/18.
 */

public class fg_four_address extends AppCompatActivity{
    ListView lv;
    private List<String> mylist = new ArrayList();
    private TextView add;//添加
    private ImageView back;//返回
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_four_address);
        lv= (ListView)findViewById(R.id.address_listview);
        add= (TextView) findViewById(R.id.tianjia);
        back= (ImageView) findViewById(R.id.adress_back);
        lv.setAdapter(new fg_four_address.LvAdapter(mylist));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(fg_four_address.this,EditAdress.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class LvAdapter extends BaseAdapter {
        private List<String> list;
        private LvAdapter(List<String> list) {
            this.list = list;
            list.add("宋家敏");
            list.add("宋家敏");
            list.add("宋家敏");
            list.add("宋家敏");
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String str = list.get(position);
            View view = View.inflate(fg_four_address.this, R.layout.address_list, null);
            TextView localTextView = (TextView) view.findViewById(R.id.ad_name);
            localTextView.setText(str);
            return view;


        }

    }
}
