package com.example.x_etc_44_46.bean;

import org.litepal.crud.LitePalSupport;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/13 19:55
 */
public class Login_ku extends LitePalSupport {

    String user;
    String pass;
    String root;
    String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
