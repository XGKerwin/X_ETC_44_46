package com.example.x_etc_44_46.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.bean.Login_ku;

import org.litepal.LitePal;

import java.util.List;

public class Activity_zhaohui extends AppCompatActivity {

    private List<Login_ku> kuList;
    private ImageView caidan;
    private TextView title;
    private ImageView shezhi;
    private TextView biaotiUser;
    private EditText edUser;
    private EditText edYouxiang;
    private Button btnZhaohui;
    private TextView txtMima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        initView();
        kuList = LitePal.findAll(Login_ku.class);

        caidan.setImageResource(R.drawable.black_exit);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        biaotiUser.setVisibility(View.GONE);
        title.setText("找回密码");
        shezhi.setVisibility(View.GONE);

        getdata();


    }

    private void getdata() {
        btnZhaohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString();
                String Email = edYouxiang.getText().toString();

                if (edUser.equals("")){
                    Toast.makeText(Activity_zhaohui.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if (edYouxiang.equals("")){
                    Toast.makeText(Activity_zhaohui.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < kuList.size(); i++) {
                        Login_ku ku = kuList.get(i);
                        Log.i("vvvvvvv", "onClick: "+ku.getUser());
                        Log.i("vvvvvvv", "onClick: "+ku.getPass());

                        if (ku.getPass().equals(user)) {
                            if (ku.getEmail().equals(Email)) {
                                txtMima.setText(ku.getUser()+"的密码为："+ku.getPass());
                                return;
                            }
                        }
                    }

                    Toast.makeText(Activity_zhaohui.this,"用户名邮箱错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        shezhi = findViewById(R.id.shezhi);
        biaotiUser = findViewById(R.id.biaoti_user);
        edUser = findViewById(R.id.ed_user);
        edYouxiang = findViewById(R.id.ed_youxiang);
        btnZhaohui = findViewById(R.id.btn_zhaohui);
        txtMima = findViewById(R.id.txt_mima);
    }
}