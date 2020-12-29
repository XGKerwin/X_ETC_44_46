package com.example.x_etc_44_46;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_44_46.fragment.Fragment_wdjt;
import com.example.x_etc_44_46.fragment.Fragment_wdzj;
import com.google.android.material.navigation.NavigationView;

public class Activity_mainzhu extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView shezhi;
    private TextView biaotiUser;
    private String user1;
    private DrawerLayout dra;
    private NavigationView nav;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainzhu);
        initView();
        user1 = getIntent().getStringExtra("1");
        title.setText("智能交通");
        shezhi.setVisibility(View.GONE);
        biaotiUser.setText("当前用户：" + user1);
        caidan.setImageResource(R.drawable.change);

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.wdzj:
                                Fragment1(new Fragment_wdzj());
                                break;
                            case R.id.wdjt:
                                Fragment1(new Fragment_wdjt());
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        shezhi = findViewById(R.id.shezhi);
        biaotiUser = findViewById(R.id.biaoti_user);
        dra = findViewById(R.id.dra);
        nav = findViewById(R.id.nav);
    }
}