package com.example.em.mi.Fragment_2;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.em.mi.R;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private String[] strings;
	public static int mPosition;
	
	public MyAdapter(Context context, String[] strings){
		this.context =context;
		this.strings = strings;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strings.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return strings[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.fg_two_list_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.tv_classification);
		mPosition = position;
		tv.setText(strings[position]);
	/*	if (position == Fragment_2.mPosition) {
			convertView.setBackgroundResource(R.color.white);
		} else {
			convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
		}*/
		return convertView;
	}

	}


