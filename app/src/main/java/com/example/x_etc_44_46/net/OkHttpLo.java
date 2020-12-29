package com.example.x_etc_44_46.net;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/13 11:03
 */
public interface OkHttpLo {


    void onResponse(JSONObject jsonObject);

    void onFailure(IOException obj);
}
