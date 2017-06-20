package com.example.em.mi.Fragment_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.em.mi.R;
import com.example.em.mi.commodit.commodity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EM on 2017/4/17.
 */

public class Fragment_1 extends Fragment implements View.OnClickListener{
    LinearLayout iguangjiebtn;
    ImageView im1;
    ListView lv;
    private LinearLayout miaosha;
    private List<String> mylist = new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_1, null);
        lv= (ListView) view.findViewById(R.id.lv);
        miaosha= (LinearLayout) view.findViewById(R.id.miaosha);
        lv.setAdapter(new LvAdapter(mylist));
        miaosha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), commodity.class);
                startActivity(i);
            }
        });



        return view;
    }
    public void onClick(View v) {

    }

    public class LvAdapter extends BaseAdapter {
        private List<String> list;
        private LvAdapter(List<String> list) {
            this.list = list;
            list.add("家敏的店");
            list.add("家敏的店2");
            list.add("家敏的店3");
            list.add("家敏的店4");
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
            View view = View.inflate(getActivity(), R.layout.fg_one_list_item, null);
            TextView localTextView = (TextView) view.findViewById(R.id.tv);
            ImageView localImageView = (ImageView)view.findViewById(R.id.li_im1);
            localTextView.setText(str);
            return view;


        }

    }

}
