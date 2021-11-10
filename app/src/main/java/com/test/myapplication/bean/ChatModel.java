package com.test.myapplication.bean;

import java.io.Serializable;


public class ChatModel implements Serializable{
    public static final String CHAT_A = "1001";
    public static final String CHAT_B = "1002";
    private int icon;
    private String content="";
    private String type="";
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
