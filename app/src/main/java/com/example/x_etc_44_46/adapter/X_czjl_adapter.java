package com.example.x_etc_44_46.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.bean.WDZJ;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 15:47
 */
public class X_czjl_adapter extends BaseAdapter {
    private List<WDZJ> wdzjList;


    public X_czjl_adapter(List<WDZJ> wdzjList) {
        this.wdzjList = wdzjList;
    }

    @Override
    public int getCount() {
        if (wdzjList.size() == 0) return 0;
        return wdzjList.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_czjl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WDZJ wdzj = wdzjList.get(position);

        holder.id.setText(position+1+"");
        holder.number.setText(wdzj.getNumber());
        holder.yue.setText(wdzj.getJine());
        holder.time.setText(wdzj.getRiqi());


        return convertView;
    }

    class ViewHolder {
        private TextView id;
        private TextView number;
        private TextView yue;
        private TextView time;

        public ViewHolder(View view) {
            id = view.findViewById(R.id.id);
            number = view.findViewById(R.id.number);
            yue = view.findViewById(R.id.yue);
            time = view.findViewById(R.id.time);
        }
    }

}
