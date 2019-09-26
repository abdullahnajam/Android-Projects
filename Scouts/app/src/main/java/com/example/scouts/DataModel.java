package com.example.scouts;

public class DataModel {
    String title,postedby;

    public DataModel(String title, String postedby) {
        this.title = title;
        this.postedby = postedby;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }
}
