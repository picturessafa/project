package com.example.em.mi.commodit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
 * Created by clevo on 2015/7/30.
 */
public class FragmentTwo  extends Fragment {
    private ListView lv;
    private List<String> mylist = new ArrayList();
    public static FragmentTwo newInstance(){
        return new FragmentTwo();
    }


    public FragmentTwo() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.detail_fragment_two,null );
        lv= (ListView) view.findViewById(R.id.de_listview);
        lv.setAdapter(new FragmentTwo.LvAdapter(mylist));
        return view;

    }
    public class LvAdapter extends BaseAdapter {
        private List<String> list;
        private LvAdapter(List<String> list) {
            this.list = list;
            list.add("李**啊");
            list.add("陈**");
            list.add("李**");
            list.add("ma**");
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
            View view = View.inflate(getActivity(), R.layout.detail_two_list_item, null);
            TextView localTextView = (TextView) view.findViewById(R.id.detail_te);
            localTextView.setText(str);
            return view;


        }

    }
}
