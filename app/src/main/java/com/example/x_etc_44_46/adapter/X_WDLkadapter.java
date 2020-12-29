package com.example.x_etc_44_46.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.bean.HLD;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 16:33
 */
public class X_WDLkadapter extends BaseAdapter {

    private List<HLD> hldList;
    private MyPeiZhi myPeiZhi;
    private Labour labour;


    public interface Labour{
        void onClick(int hv , int position);
    }

    public void setLabour(Labour labour){
        this.labour = labour;
    }

    public interface MyPeiZhi{
        void onClick(int number);
    }

    public void setMyPeiZhi(MyPeiZhi myPeiZhi){
        this.myPeiZhi = myPeiZhi;
    }

    public X_WDLkadapter(List<HLD> hldList) {
        this.hldList = hldList;
    }

    @Override
    public int getCount() {
        return hldList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wdlk,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final HLD hld = hldList.get(position);
        holder.hong.setText("红灯"+hld.red+"秒");
        holder.huang.setText("黄灯"+hld.yellow+"秒");
        holder.lv.setText("绿灯"+hld.green+"秒");
        holder.luKou.setText("路口"+hld.number);
        holder.hengZhuangTai.setText(hld.horizontal);
        holder.hengTime.setText(hld.hTime);
        holder.zhongZhuangTai.setText(hld.vertical);
        holder.zhongTime.setText(hld.vTime);

        holder.hengTime.setBackgroundResource(hld.imageH);
        holder.zhongTime.setBackgroundResource(hld.imageV);

        holder.peiZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPeiZhi.onClick(hld.number);
            }
        });

        holder.hengxiang.setEnabled(hld.isEnableH);
        holder.hengxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPeiZhi.onClick(hld.number);
            }
        });

        holder.hengxiang.setEnabled(hld.isEnableH);
        holder.hengxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labour.onClick(1 , position);
                hld.hvH = 1;
                hld.position1 = position;
                hld.daoJiShiH = 30;
                hld.isEnableH = false;
            }
        });

        holder.zhongXiang.setEnabled(hld.isEnableV);
        holder.zhongXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hld.isEnableV = false;
                hld.daoJiShiV = 30;
                hld.hvV = 2;
                hld.position1 = position;
                labour.onClick(2 , position);
            }
        });



        return convertView;
    }

    class ViewHolder{
        private TextView luKou , lv , huang , hong , hengZhuangTai , zhongZhuangTai ,
                hengTime , zhongTime;
        private Button hengxiang , zhongXiang , peiZhi;

        public ViewHolder(View view) {
            luKou = view.findViewById(R.id.wdlk_lu_kou);
            lv = view.findViewById(R.id.wdlk_lv);
            huang = view.findViewById(R.id.wdlk_huang);
            hong = view.findViewById(R.id.wdlk_hong);
            hengZhuangTai = view.findViewById(R.id.wdlk_heng);
            zhongZhuangTai = view.findViewById(R.id.wdlk_zhong);
            hengTime = view.findViewById(R.id.wdlk_heng_time);
            zhongTime = view.findViewById(R.id.wdlk_zhong_time);
            hengxiang = view.findViewById(R.id.wdlk_heng_xiang);
            zhongXiang = view.findViewById(R.id.wdlk_zhong_xiang);
            peiZhi = view.findViewById(R.id.wdlk_pei_zhi);
        }
    }
}
