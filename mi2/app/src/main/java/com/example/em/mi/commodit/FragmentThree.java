package com.example.em.mi.commodit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.em.mi.R;

/**
 * Created by clevo on 2015/7/30.
 */
public class FragmentThree extends Fragment {


    public static FragmentThree newInstance(){
        return new FragmentThree();
    }


    public FragmentThree() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment_three,container,false );
    }
}
