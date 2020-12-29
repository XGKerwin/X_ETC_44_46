package com.example.x_etc_44_46.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_44_46.bean.HLD;
import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.adapter.X_WDLkadapter;
import com.example.x_etc_44_46.net.OkHttpLo;
import com.example.x_etc_44_46.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 16:15
 */

public class Fragment_wdlk extends Fragment {

    private ListView listview;
    private List<HLD> hldList;
    private X_WDLkadapter adapter;
    private Thread thread;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdlk, null);
        initView(view);

        getfor();

        return view;
    }

    private void getfor() {
        if (hldList==null){
            hldList = new ArrayList<>();
        }else {
            hldList.clear();
        }

        for (int i=1;i<=5;i++){
            getOkHttp(i);
        }
    }

    private void getOkHttp(int i) {
        new OkHttpTo()
                .setUrl("get_traffic_light")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hldList.addAll((Collection<? extends HLD>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString() ,
                                new TypeToken<List<HLD>>(){}.getType()));
                        HLD hld = hldList.get(hldList.size()-1);
                        getH(hld);
                        getV(hld);
                        if (hldList.size() == 5){
                            Collections.sort(hldList, new Comparator<HLD>() {
                                @Override
                                public int compare(HLD o1, HLD o2) {
                                    return o1.number - o2.number;
                                }
                            });
                            setThread();
                        }
                        
                        
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setThread() {
        if (thread == null){
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    do {
                        handler.sendEmptyMessage(0);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }while (true);
                }
            });
            thread.start();
        }
    }

    private boolean isLoop = true;
    private int hvH , hvV;
    private int position1;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (adapter == null){
                adapter = new X_WDLkadapter(hldList);
                listview.setAdapter(adapter);
                adapter.setMyPeiZhi(new X_WDLkadapter.MyPeiZhi() {
                    @Override
                    public void onClick(int number) {
                        showDiaLog(number);
                    }
                });
                adapter.setLabour(new X_WDLkadapter.Labour() {
                    @Override
                    public void onClick(int hv, int position) {
                        if (hv == 1){
                            hvH = hv;
                        }else if (hv == 2){
                            hvV = hv;
                        }
                        position1 = position;
                    }
                });
            }else{
                adapter.notifyDataSetChanged();
            }
            for (int i = 0 ; i < hldList.size() ; i++){
                HLD hld = hldList.get(i);
                if (hld.hvH == 1 && i == hld.position1) {
                    if (hld.daoJiShiH > 0){
                        hld.imageH = R.drawable.lvdeng;
                        hld.horizontal = "横向状态        绿灯" + hld.daoJiShiH + "秒";
                        hld.hTime = hld.daoJiShiH + "";
                        hld.daoJiShiH--;
                    }else {
                        hld.hvH = -1;
                        hld.isEnableH = true;
                    }
                }else {
                    if (hld.greenH > 0) {
                        hld.imageH = R.drawable.lvdeng;
                        hld.horizontal = "横向状态        绿灯" + hld.greenH + "秒";
                        hld.hTime = hld.greenH + "";
                        hld.greenH--;
                    } else if (hld.yellowH > 0) {
                        hld.imageH = R.drawable.huangdeng;
                        hld.horizontal = "横向状态        黄灯" + hld.yellowH + "秒";
                        hld.hTime = hld.yellowH + "";
                        hld.yellowH--;
                    } else if (hld.redH > 0) {
                        hld.imageH = R.drawable.hongdeng;
                        hld.horizontal = "横向状态        红灯" + hld.redH + "秒";
                        hld.hTime = hld.redH + "";
                        hld.redH--;
                    } else {
                        getH(hld);
                    }
                }

                if (hld.hvV == 2 && i == hld.position1) {
                    if (hld.daoJiShiV > 0){
                        hld.imageV = R.drawable.lvdeng;
                        hld.vertical = "纵向状态        绿灯" + hld.daoJiShiV + "秒";
                        hld.vTime = hld.daoJiShiV + "";
                        hld.daoJiShiV--;
                    }else {
                        hld.hvV = -1;
                        hld.isEnableV = true;
                    }
                }else {
                    if (hld.yellowV > 0){
                        hld.imageV = R.drawable.huangdeng;
                        hld.vertical = "纵向状态        黄灯"+hld.yellowV+"秒";
                        hld.vTime = hld.yellowV+"";
                        hld.yellowV--;
                    } else  if (hld.redV > 0){
                        hld.imageV = R.drawable.hongdeng;
                        hld.vertical = "纵向状态        红灯"+hld.redV+"秒";
                        hld.vTime = hld.redV+"";
                        hld.redV--;
                    } else if (hld.greenV > 0){
                        hld.imageV = R.drawable.lvdeng;
                        hld.vertical = "纵向状态        绿灯"+hld.greenV+"秒";
                        hld.vTime = hld.greenV+"";
                        hld.greenV--;
                    }else {
                        getV(hld);
                    }
                }
                hldList.set(i , hld);
            }

            return false;
        }
    });

    private void showDiaLog(final int number) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(getContext() , R.layout.dia_wdjt, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final EditText hong = view.findViewById(R.id.pz_hong);
        final EditText huang = view.findViewById(R.id.pz_huang);
        final EditText lv = view.findViewById(R.id.pz_lv);

        hong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int s1 = Integer.parseInt(s.toString().trim());
                    if (s1 > 30 || s1 == 0) {
                        Toast.makeText(getContext(), "请输入1到30之间的整数", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        huang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    int s1 = Integer.parseInt(s.toString().trim());
                    if (s1 > 30 || s1 == 0) {
                        Toast.makeText(getContext(), "请输入1到30之间的整数", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        lv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    int i1 = Integer.parseInt(s.toString().trim());
                    if (i1>30 || i1==0){
                        Toast.makeText(getContext(),"请输入1到30之内的整数",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button queding = view.findViewById(R.id.pz_qd);
        Button qx = view.findViewById(R.id.pz_qx);


        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hong.getText().toString().equals("")){
                    Toast.makeText(getContext() , "红灯秒数不能为空" , Toast.LENGTH_SHORT).show();
                }else if (huang.getText().toString().equals("")){
                    Toast.makeText(getContext() , "黄灯秒数不能为空" , Toast.LENGTH_SHORT).show();
                }else if (lv.getText().toString().equals("")){
                    Toast.makeText(getContext() , "绿灯秒数不能为空" , Toast.LENGTH_SHORT).show();
                }else {
                    dialog.dismiss();
                    setHLD(hong.getText().toString() , huang.getText().toString() , lv.getText().toString() , number);
                }
            }
        });

        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void setHLD(String hong, String huang, String lv, int number) {
        //{"UserName":"user1","number":"1","red":"1","yellow":"1","green":"1"}
        new OkHttpTo()
                .setUrl("set_traffic_light")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",number)
                .setJsonObject("hong",hong)
                .setJsonObject("huang",huang)
                .setJsonObject("lv",lv)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")){
                            getfor();
                            Toast.makeText(getContext(),"配置成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(),"配置失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getV(HLD hld) {
        hld.imageV = R.drawable.huangdeng;
        hld.getV();
        hld.vertical = "纵向状态        黄灯"+hld.yellowV+"秒";
        hld.vTime = hld.yellowV+"";
        hld.yellowV--;
    }

    private void getH(HLD hld) {
        hld.imageH = R.drawable.lvdeng;
        hld.getH();
        hld.horizontal =  "横向状态        绿灯"+hld.greenH+"秒";
        hld.hTime = hld.greenH+"";
        hld.greenH--;
    }

    private void initView(View view) {
        listview = view.findViewById(R.id.listview);
    }
}
