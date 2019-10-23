package com.fyp.quickrepair;

public class DataModel {
    String title,description;
    int photoId;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public DataModel(String title, String description, int photoId) {
        this.title = title;
        this.description = description;
        this.photoId=photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
