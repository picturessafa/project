package com.example.em.mi.Fragment_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.em.mi.R;

/**
 * Created by EM on 2017/5/2.
 */

public class EditAdress extends AppCompatActivity{
    private ImageView back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bianji_address);
        back= (ImageView) findViewById(R.id.edit_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
