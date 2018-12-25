package com.example.arsalanabrar.firebasedatabase;

public class Userdata {
    String userid;
    String user_name;
    String phoneno;
    String password;
    public Userdata(){

    }

    public Userdata(String userid, String user_name, String phoneno, String password) {
        this.userid = userid;
        this.user_name = user_name;
        this.phoneno = phoneno;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getPassword() {
        return password;
    }
}
