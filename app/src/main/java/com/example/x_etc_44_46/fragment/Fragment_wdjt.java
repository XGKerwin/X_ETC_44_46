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
 * date   : 2020/12/14 16:07
 */

public class Fragment_wdjt extends Fragment {

    private TextView txtWdlk;
    private TextView txtDlhj;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdjt, null);
        initView(view);

        txtWdlk.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        txtDlhj.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Fragment1(new Fragment_wdlk());

        btn();


        return view;
    }

    private void btn() {

        txtWdlk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWdlk.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                txtDlhj.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                Fragment1(new Fragment_wdlk());
            }
        });

        txtDlhj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWdlk.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                txtDlhj.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                Fragment1(new Fragment_dlhj());
            }
        });


    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment2,fragment).commit();
    }

    private void initView(View view) {
        txtWdlk = view.findViewById(R.id.txt_wdlk);
        txtDlhj = view.findViewById(R.id.txt_dlhj);
    }
}
