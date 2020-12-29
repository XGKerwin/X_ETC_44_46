package com.example.x_etc_44_46.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_44_46.Activity_mainzhu;
import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.bean.Login;
import com.example.x_etc_44_46.bean.Login_ku;
import com.example.x_etc_44_46.net.OkHttpLo;
import com.example.x_etc_44_46.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView caidan;
    private ImageView shezhi;
    private TextView biaotiUser;
    private EditText edUser;
    private EditText edPass;
    private TextView zhuce;
    private TextView zhaohui;
    private Button denglu;
    private List<Login> logins;
    private List<Login_ku> kuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        kuList = LitePal.findAll(Login_ku.class);

        caidan.setVisibility(View.GONE);
        biaotiUser.setVisibility(View.GONE);

        getOkHttp();

        btn();


    }

    private String user1;

    private void btn() {

        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString();
                String pass = edPass.getText().toString();
                if (user.equals("")){
                    Toast.makeText(MainActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    for (int i=0;i<logins.size();i++){
                        Login login = logins.get(i);
                        if (user.equals(login.getUsername())){
                            if (pass.equals(login.getPassword())){
                                user1 = login.getRoot();
                                Log.i("vccxxxxxxxxxxx", "onClick: "+login.getRoot());
                                biaotiUser.setText(user1);
                                Intent intent = new Intent(MainActivity.this, Activity_mainzhu.class)
                                        .putExtra("1",user1);
                                startActivity(intent);
                                return;
                            }
                        }
                    }

                    for (int i=0;i<kuList.size();i++){
                        Login_ku ku = kuList.get(i);
                        if (user.equals(ku.getUser())){
                            if (pass.equals(ku.getPass())){
                                user1 = ku.getRoot();
                                biaotiUser.setText(user1);
                                Intent intent = new Intent(MainActivity.this,Activity_mainzhu.class)
                                        .putExtra("1",user1);
                                startActivity(intent);
                                return;
                            }
                        }
                    }

                    Toast.makeText(MainActivity.this,"用户名密码错误",Toast.LENGTH_SHORT).show();


                }
            }
        });

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_zhuce.class);
                startActivity(intent);
            }
        });

        zhaohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_zhaohui.class);
                startActivity(intent);
            }
        });

    }

    private void getOkHttp() {
        if (logins == null){
            logins = new ArrayList<>();
        }else {
            logins.clear();
        }
        new OkHttpTo()
                .setUrl("get_login")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        logins.addAll((Collection<? extends Login>) new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                new TypeToken<List<Login>>(){}.getType()));

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        shezhi = findViewById(R.id.shezhi);
        biaotiUser = findViewById(R.id.biaoti_user);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        zhuce = findViewById(R.id.zhuce);
        zhaohui = findViewById(R.id.zhaohui);
        denglu = findViewById(R.id.denglu);
    }
}