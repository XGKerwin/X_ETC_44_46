package com.example.x_etc_44_46.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/14 16:26
 */
public class HLD {

    /**
     *             "id": 5,
     *             "number": 5,
     *             "red": 13,
     *             "yellow": 3,
     *             "green": 16
     */

    public int id;
    public int number;
    public int red;
    public int yellow;
    public int green;

    public int redH;
    public int yellowH;
    public int greenH;

    public int redV;
    public int yellowV;
    public int greenV;

    public int hvH = -1, hvV = -1;
    public int position1 = -1;

    public boolean isEnableH = true , isEnableV = true;

    public String vertical , horizontal;
    public String vTime , hTime;
    public int imageH , imageV;
    public int daoJiShiH , daoJiShiV;

    public void getH(){
        redH = red;
        yellowH = yellow;
        greenH = green;
    }

    public void getV(){
        redV = red;
        yellowV = yellow;
        greenV = green;
    }


}
