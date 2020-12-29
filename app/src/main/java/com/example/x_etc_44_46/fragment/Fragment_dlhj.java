package com.example.x_etc_44_46.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_44_46.R;
import com.example.x_etc_44_46.net.OkHttpLo;
import com.example.x_etc_44_46.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 8:24
 */
public class Fragment_dlhj extends Fragment {

    private TextView txtPm;
    private TextView txtTishi;
    private VideoView video;
    private TextView txtGuang;
    private TextView txtTishi2;
    private TextView txtGuangzhao;
    private TextView txtGuangzhaozhi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_dlhj, null);
        initView(view);
        video.setVideoURI(Uri.parse("android://resource://"+getActivity().getPackageName()+"/"+R.raw.xiaoshipin));
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                video.start();
            }
        });


        getOkHttp();




        return view;
    }

    private void getOkHttp() {
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtPm.setText("PM2.5当前值："+jsonObject.optString("pm25"));
                        int pm = jsonObject.optInt("pm25");
                        if (pm>200){
                            txtTishi.setText("不适合出行");
                            video.setVisibility(View.VISIBLE);
                            video.start();
                        }else {
                            txtTishi.setText("");
                            video.setVisibility(View.INVISIBLE);
                        }

                        int guang = jsonObject.optInt("illumination");
                        txtGuang.setText("光照强度当前值："+guang);
                        if (guang<1100){
                            txtTishi2.setText("光照较弱，出行请开灯");
                        }else if (guang>3190){
                            txtTishi2.setText("光照较强，出行请戴墨镜");
                        }else {
                            txtTishi2.setText("适合出行");
                        }

                        txtGuangzhao.setPadding((guang / 10), 0, 0, 0);

                        txtGuangzhaozhi.setPadding((guang / 10) - 50, 0, 0, 0);
                        txtGuangzhaozhi.setText(guang + "");


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        txtPm = view.findViewById(R.id.txt_pm);
        txtTishi = view.findViewById(R.id.txt_tishi);
        video = view.findViewById(R.id.video);
        txtGuang = view.findViewById(R.id.txt_guang);
        txtTishi2 = view.findViewById(R.id.txt_tishi2);
        txtGuangzhao = view.findViewById(R.id.txt_guangzhao);
        txtGuangzhaozhi = view.findViewById(R.id.txt_guangzhaozhi);
    }
}
