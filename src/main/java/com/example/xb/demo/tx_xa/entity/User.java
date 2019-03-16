package com.example.xb.demo.tx_xa.entity;

public class User {
    private int id;
    private String user_name;
    private String pass_word;

    public User(int id, String user_name, String pass_word) {
        this.id = id;
        this.user_name = user_name;
        this.pass_word = pass_word;
    }

    public User() {
    }

    public User(String user_name, String pass_word) {
        this.user_name = user_name;
        this.pass_word = pass_word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

}
