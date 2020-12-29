package com.example.x_etc_44_46.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_44_46.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 15:14
 */
public class X_list_adapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_yckz, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.number.setText(position+1+"");



        return convertView;
    }

    class ViewHolder {
        private TextView number;
        private TextView btnQidong;
        private TextView btnTingzhi;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            btnQidong = view.findViewById(R.id.btn_qidong);
            btnTingzhi = view.findViewById(R.id.btn_tingzhi);
        }
    }
}
