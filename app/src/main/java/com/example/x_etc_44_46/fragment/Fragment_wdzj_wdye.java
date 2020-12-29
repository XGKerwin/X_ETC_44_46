package com.example.x_etc_44_46.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.adapter.X_WDZJ_adapter;
import com.example.x_etc_44_46.bean.WDZJ;
import com.example.x_etc_44_46.bean.WDZJ_yue;
import com.example.x_etc_44_46.net.OkHttpLo;
import com.example.x_etc_44_46.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 9:15
 */
public class Fragment_wdzj_wdye extends Fragment {

    private GridView gridview;
    private List<WDZJ_yue> list;
    private X_WDZJ_adapter adapter;


    private Button btnQuxiao;
    private Button btnQueding;
    private EditText edJine;
    private ImageView diaBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wdzj_jilu, null);
        initView(view);

        getOkHttp();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dia, null);
                builder.setView(view1);
                builder.setCancelable(false);

                diaBack = view1.findViewById(R.id.dia_back);
                edJine = view1.findViewById(R.id.ed_jine);
                btnQueding = view1.findViewById(R.id.btn_queding);
                btnQuxiao = view1.findViewById(R.id.btn_quxiao);

                edJine.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().equals("0")){
                            edJine.setText("");
                            Toast.makeText(getContext(),"请输入1-50内的整数",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                final AlertDialog dialog = builder.show();

                diaBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnQuxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnQueding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String jine = edJine.getText().toString();
                        String cp = list.get(position).getPlate();
                        setOkHttp(jine,cp,dialog,position);
                    }
                });



            }
        });

        return view;
    }

    private void setOkHttp(final String jine, String cp, final AlertDialog dialog, final int position) {
        //{"UserName":"user1","plate":"鲁A10001","balance":"100"}
        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("plate", cp)
                .setJsonObject("balance", jine)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getTime();
                        WDZJ wdzj = new WDZJ();
                        wdzj.setJine(jine);
                        wdzj.setNumber(position+1+"");
                        wdzj.setRiqi(time);
                        if (wdzj.save()){
                            Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getOkHttp();
                        }else {
                            Toast.makeText(getContext(),"保存失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private String time;
    private void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        time = simpleDateFormat.format(date);
    }

    private void getOkHttp() {
        //{"UserName":"user1"}
        if (list == null) {
            list = new ArrayList<>();
        } else {
            list.clear();
        }

        new OkHttpTo()
                .setUrl("get_vehicle")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        list.addAll((Collection<? extends WDZJ_yue>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<WDZJ_yue>>() {
                                }.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void showList() {
        adapter = new X_WDZJ_adapter(list);
        gridview.setAdapter(adapter);
    }

    private void initView(View view) {
        gridview = view.findViewById(R.id.gridview);
    }
}
