package com.example.jp.regform_app;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by JP on 24-Nov-17.
 */
public class Data_Model extends AppCompatActivity {
    int id;
    String name;
    String address;
    String mobile;
    byte[] imbyte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public byte[] getImbyte() {
        return imbyte;
    }

    public void setImbyte(byte[] imbyte) {
        this.imbyte = imbyte;
    }
}
