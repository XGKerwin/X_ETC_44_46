package com.example.x_etc_44_46.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.bean.Login_ku;

public class Activity_zhuce extends AppCompatActivity {

    private ImageView caidan;
    private ImageView shezhi;
    private TextView biaotiUser;
    private EditText edUser;
    private EditText edEmail;
    private EditText edPass1;
    private EditText edPass2;
    private Button btnDenglu;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        biaotiUser.setVisibility(View.VISIBLE);
        shezhi.setVisibility(View.VISIBLE);
        title.setText("注册");
        caidan.setImageResource(R.drawable.black_exit);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = edUser.getText().toString();
                String pass = edPass1.getText().toString();
                String pass1 = edPass2.getText().toString();
                String E_mail = edEmail.getText().toString();
                if (user1.equals("")) {
                    Toast.makeText(Activity_zhuce.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Toast.makeText(Activity_zhuce.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (pass1.equals("")) {
                    Toast.makeText(Activity_zhuce.this, "请确认密码", Toast.LENGTH_SHORT).show();
                } else if (E_mail.equals("")) {
                    Toast.makeText(Activity_zhuce.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                } else if (pass.equals(pass1)) {
                    Login_ku login = new Login_ku();
                    login.setUser(user1);
                    login.setEmail(E_mail);
                    login.setPass(pass);
                    login.setRoot("用户");
                    if (login.save()){
                        edUser.setText("");
                        edPass1.setText("");
                        edPass2.setText("");
                        edEmail.setText("");
                        Toast.makeText(Activity_zhuce.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Activity_zhuce.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Activity_zhuce.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        shezhi = findViewById(R.id.shezhi);
        biaotiUser = findViewById(R.id.biaoti_user);
        edUser = findViewById(R.id.ed_user);
        edEmail = findViewById(R.id.ed_email);
        edPass1 = findViewById(R.id.ed_pass1);
        edPass2 = findViewById(R.id.ed_pass2);
        btnDenglu = findViewById(R.id.btn_denglu);
        title = findViewById(R.id.title);
    }
}