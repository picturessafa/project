package com.example.em.mi.service;

import android.app.Dialog;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EM on 2017/4/26.
 */

public class Mysy extends AsyncTask {
    //先定义一个变量用于保存服务器返回来的信息
    String url = "http://10.6.162.42:8080/HelloWeb/Register";
    String result = "";
    private Dialog pd;
    String su;
    //进度条


    //查询SQL交互的操作
    @Override
    protected Object doInBackground(Object... arg0) {
       // String username = arg0[0].toString();
        String phoneNum = arg0[0].toString();
        String  password= arg0[1].toString();

        Log.i("用户信息",phoneNum+"+用户信息+"+password);

        if(TextUtils.isEmpty(phoneNum)!=true && TextUtils.isEmpty(password)!=true){
            Log.i("用户信息1",phoneNum+"+用户信息+"+password);
            //给服务器发送请求
            HttpPost request = new HttpPost(url);
            //创建请求对象参数  键值对NameValuePair
            List<NameValuePair> paras = new ArrayList<NameValuePair>();
            //保存数据的容器
          //  BasicNameValuePair user = new BasicNameValuePair("username", username);
            BasicNameValuePair phone = new BasicNameValuePair("phoneNum", phoneNum);
            BasicNameValuePair pass = new BasicNameValuePair("password", password);


            //将参数添加到List中
         //   paras.add(user);
            paras.add(phone);
            paras.add(pass);

            //创建一个请求标志
          //  BasicNameValuePair flag = new BasicNameValuePair("flag","1");
          //  paras.add(flag);
            //创建数据实体
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paras, "GBK");
                //将数据实体放入到请求对象里面
                request.setEntity(entity);
                //创建Http的客户对象
                DefaultHttpClient dhc = new DefaultHttpClient();
                //创建一个响应对象接受服务器返回的信息
                HttpResponse response = dhc.execute(request);
                //定义一个变量保存连接服务器的状态
                int status = response.getStatusLine().getStatusCode();
                //根据状态码的值判断服务器是否连接成功
                Log.i("status",status+"");
                if(status == 200){
                    //定义一个字节流数据用于保存从相应对象中解释出来的数据
                    byte[] b = new byte[2040];
                    //获取响应对象中数据
                    b = EntityUtils.toByteArray(response.getEntity());
                    //将字节流转换成字符串  服务器统一编码
                    result = new String(b,"GBK");
                    su = "成功";
                }else{
                    su = "不成功";
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Log.i("result",result);
        return result;
    }
    //隐藏进度条
    @Override
    protected void onPostExecute(Object result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
   //     pd.dismiss();
      //  tv_tip.setText(result+""+su);
    }
}