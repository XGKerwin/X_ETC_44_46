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
import com.example.x_etc_44_46.adapter.X_list_adapter;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 9:14
 */
public class Fragment_wdzj_yckz extends Fragment {

    private ListView list;
    private X_list_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdzj_yuancheng, null);
        initView(view);

        adapter = new X_list_adapter();
        list.setAdapter(adapter);



        return view;
    }

    private void initView(View view) {
        list = view.findViewById(R.id.list);
    }
}
