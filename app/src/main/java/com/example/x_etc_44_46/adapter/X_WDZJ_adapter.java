package com.example.x_etc_44_46.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.bean.WDZJ_yue;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 9:24
 */
public class X_WDZJ_adapter extends BaseAdapter {

    private List<WDZJ_yue> list;

    public X_WDZJ_adapter(List<WDZJ_yue> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wdzj, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WDZJ_yue yue = list.get(position);

        holder.txtNumber.setText(yue.getNumber()+"号小车");
        holder.txtYue.setText(yue.getBalance()+"");
        if (yue.getBalance()>=100){
            holder.lin.setBackgroundResource(R.drawable.hong_hei);
            holder.txtZhuangtai.setText("警告");
        }else {
            holder.lin.setBackgroundResource(R.drawable.lv_hei);
            holder.txtZhuangtai.setText("正常");
        }

        return convertView;
    }


    class ViewHolder {
        private LinearLayout lin;
        private TextView txtNumber;
        private TextView txtZhuangtai;
        private TextView txtYue;

        public ViewHolder(View view) {
            lin = view.findViewById(R.id.lin);
            txtNumber = view.findViewById(R.id.txt_number);
            txtZhuangtai = view.findViewById(R.id.txt_zhuangtai);
            txtYue = view.findViewById(R.id.txt_yue);
        }
    }

}
