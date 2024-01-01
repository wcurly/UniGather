package com.we.UniGather.models;

public class UserLoginDTO {
    private String email;
    private String password;

    // Constructors, getters, setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginDTO() {
        // 默认构造函数
    }

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
