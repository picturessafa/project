package com.example.em.mi;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.em.mi.service.WebService;

/**
 * Created by EM on 2017/4/26.
 */

public class Login extends AppCompatActivity implements View.OnClickListener{
    // 登陆按钮
    private Button logbtn;
    // 调试文本，注册文本
    private TextView infotv, regtv;
    // 显示用户名和密码
    EditText phoneNum, password;
    // 创建等待框
    private ProgressDialog dialog;
    // 返回的数据
    private String info;
    // 返回主线程更新数据
    private static Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // 获取控件
        phoneNum = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        logbtn = (Button) findViewById(R.id.login);
        regtv = (TextView) findViewById(R.id.register);
        infotv = (TextView) findViewById(R.id.info);

        // 设置按钮监听器
        logbtn.setOnClickListener(this);
        regtv.setOnClickListener(this);

    }

//登录按钮监听
    @Override
    public void onClick(View v) {
        String user=phoneNum.getText().toString();
        String pass=password.getText().toString();
        switch (v.getId()) {
            case R.id.login:
                // 检测网络，无法检测wifi
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(Login.this,"网络未连接", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                }else if(user.length()<8){
                    logbtn.setEnabled(false);
                    Toast.makeText(getApplicationContext(),R.string.login_b1,Toast.LENGTH_SHORT).show();
                }else if(pass.length()<6){
                    Toast.makeText(getApplicationContext(),"密码不能小于6位",Toast.LENGTH_SHORT).show();

                }else {
                    // 提示框
                    logbtn.setEnabled(true);
                    dialog = new ProgressDialog(this);
                    dialog.setTitle("提示");
                    dialog.setMessage("正在登陆，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    new Thread(new MyThread()).start();
                }           // 创建子线程，分别进行Get和Post传输;


                break;

                        case R.id.register:
                        Intent regItn = new Intent(Login.this, registerActivity.class);
        startActivity(regItn);
        break;
        }
        ;
        }
// 子线程接收数据，主线程修改数据
private class MyThread implements Runnable {
    @Override
    public void run() {
        String phone=phoneNum.getText().toString();
        String pass= password.getText().toString();
        info = WebService.executeHttpGet(phone,pass);

        handler.post(new Runnable() {
            @Override
            public void run() {
                //    infotv.setText(info);
                if (info == null) {
                    Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                } else {
                    if (info==null) {
                        Toast.makeText(getApplicationContext(), "网络未连接", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (info.equals("1")) {
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    } else if (info.equals("0")) {
                        Toast.makeText(getApplicationContext(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_SHORT).show();
                    }
//                Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }
}

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
