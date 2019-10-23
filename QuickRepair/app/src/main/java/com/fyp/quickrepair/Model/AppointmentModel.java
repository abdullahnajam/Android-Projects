package com.fyp.quickrepair.Model;

public class AppointmentModel {
    public String username,email,phone;
    public long now,time;
    public String address,lattitude,longitude;

    public AppointmentModel(String username, String email, String phone, long now, long time, String address, String lattitude, String longitude) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.now = now;
        this.time = time;
        this.address = address;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public long getNow() {
        return now;
    }

    public long getTime() {
        return time;
    }

    public AppointmentModel() {
    }
}
