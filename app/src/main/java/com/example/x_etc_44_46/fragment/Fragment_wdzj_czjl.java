package com.example.x_etc_44_46.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.adapter.X_czjl_adapter;
import com.example.x_etc_44_46.bean.WDZJ;

import org.litepal.LitePal;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 9:12
 */
public class Fragment_wdzj_czjl extends Fragment {
    private ListView listview;
    private X_czjl_adapter adapter;
    private List<WDZJ> wdzjList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdzj_yue, null);
        initView(view);
        wdzjList = LitePal.findAll(WDZJ.class);

        adapter = new X_czjl_adapter(wdzjList);
        listview.setAdapter(adapter);


        return view;
    }

    private void initView(View view) {
        listview = view.findViewById(R.id.listview);
    }
}
