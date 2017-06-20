package com.example.em.mi.Fragment_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.em.mi.R;



public class Fragment_4 extends Fragment implements View.OnClickListener{
    ImageView fg4_cirimg;
    RelativeLayout m_btn01,m_btn02,m_btn03,m_btn04;
    LinearLayout mlay04_dindan,mlay04_dindan02,mlay04_dindan03,mlay04_dindan04,
            mlay04_dindan05,mlay04_dindan06,mlay04_dindan07,mlay04_dindan08;
    TextView yong;
    Bitmap bmlogo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_4,null);
        fg4_cirimg= (ImageView) view.findViewById(R.id.fg4_cirimg);
        bmlogo = BitmapFactory.decodeFile(fg_four_gravatar.newPath);
        fg4_cirimg.setImageBitmap(bmlogo);
        yong = ((TextView)view.findViewById(R.id.yong));
        m_btn01 = ((RelativeLayout)view.findViewById(R.id.m_btn01));
        m_btn02 = ((RelativeLayout)view.findViewById(R.id.m_btn02));
        m_btn03 = ((RelativeLayout)view.findViewById(R.id.m_btn03));
        m_btn04 = ((RelativeLayout)view.findViewById(R.id.m_btn04));
        mlay04_dindan = ((LinearLayout)view.findViewById(R.id.mlay04_dindan));
        mlay04_dindan02 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan02));
        mlay04_dindan03 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan03));
        mlay04_dindan04 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan04));
        mlay04_dindan05 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan05));
        mlay04_dindan06 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan06));
        mlay04_dindan07 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan07));
        mlay04_dindan08 = ((LinearLayout)view.findViewById(R.id.mlay04_dindan08));


        fg4_cirimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(getActivity(), fg_four_gravatar.class);
                startActivity(i);
            }
        });
        m_btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), fg_four_aboutus.class);
                startActivity(i);
            }
        });
        m_btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), fg_four_address.class);
                startActivity(i);
            }
        });
        m_btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  fg_four_setting.class);
                startActivity(i);
            }
        });
        m_btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), fg_four_aboutus.class);
                startActivity(i);
            }
        });
        mlay04_dindan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), dindan.class);
                startActivity(i);
            }
        });
        mlay04_dindan02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), dindan.class);
                startActivity(i);
            }
        });
        mlay04_dindan03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), dindan.class);
                startActivity(i);
            }
        });
        mlay04_dindan04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), dindan.class);
                startActivity(i);
            }
        });
        mlay04_dindan05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), dindan.class);
                startActivity(i);
            }
        });
        mlay04_dindan06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), fg_four_message.class);
                startActivity(i);
            }
        });
        mlay04_dindan07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), fg_four_coupon.class);
                startActivity(i);
            }
        });
        mlay04_dindan08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),  aftermarket.class);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
