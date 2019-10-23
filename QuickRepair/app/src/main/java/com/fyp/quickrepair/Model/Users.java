package com.fyp.quickrepair.Model;

public class Users {
    public String name,phone,type;
    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users(String username, String phone, String type) {
        this.name = username;
        this.phone = phone;
        this.type=type;
    }

    public Users() {
    }
}
