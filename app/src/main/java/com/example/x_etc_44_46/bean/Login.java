package com.example.x_etc_44_46.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/13 11:09
 */
public class Login {

    /**
     *             "id": 1,
     *             "username": "user1",
     *             "password": "123456",
     *             "root": "管理员"
     */

    private String id,username,password,root;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
