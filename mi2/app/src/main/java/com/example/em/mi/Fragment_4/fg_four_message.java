package com.example.em.mi.Fragment_4;

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
 * Created by EM on 2017/4/19.
 */

public class fg_four_message extends AppCompatActivity {
    private ListView lv;
    private List<String> mylist = new ArrayList();
    private ImageView mess;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_four_message);
        lv = (ListView) findViewById(R.id.lv);
        mess= (ImageView) findViewById(R.id.mess);
        lv.setAdapter(new LvAdapter(mylist));
        mess.setOnClickListener(new View.OnClickListener() {
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
            list.add("通知");
            list.add("官方消息");
            list.add("好友的消息1");
            list.add("好友的消息2");
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
            View view = View.inflate(fg_four_message.this, R.layout.fg_four_message_list_item, null);
            TextView localTextView = (TextView) view.findViewById(R.id.ik);
            //  ImageView localImageView = (ImageView)view.findViewById(R.id.li_im1);
            localTextView.setText(str);
            return view;


        }
    }
}