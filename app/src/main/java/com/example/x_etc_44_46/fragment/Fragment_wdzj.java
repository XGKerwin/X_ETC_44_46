package com.example.x_etc_44_46.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_44_46.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 8:57
 */
public class Fragment_wdzj extends Fragment {

    private TextView txtYue;
    private TextView txtYuancheng;
    private TextView txtJilu;
    private TextView title;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdzj, null);
        initView(view);
        title.setText("我的座驾");
        txtYue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        btn();

        Fragment1(new Fragment_wdzj_wdye());

        return view;
    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment1,fragment).commit();
    }

    private void btn() {

        txtYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1(new Fragment_wdzj_wdye());
                txtYue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                txtJilu.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                txtYuancheng.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });

        txtJilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1(new Fragment_wdzj_czjl());
                txtYue.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                txtJilu.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                txtYuancheng.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });

        txtYuancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1(new Fragment_wdzj_yckz());
                txtYue.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                txtJilu.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                txtYuancheng.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
        });

    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        txtYue = view.findViewById(R.id.txt_yue);
        txtYuancheng = view.findViewById(R.id.txt_yuancheng);
        txtJilu = view.findViewById(R.id.txt_jilu);
    }
}
