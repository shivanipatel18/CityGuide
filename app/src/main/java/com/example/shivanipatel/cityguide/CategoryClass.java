package com.example.shivanipatel.cityguide;

/**
 * Created by shivani.patel on 04-05-2016.
 */
public class CategoryClass {

    String title;
    int imageid;

    public CategoryClass(String title, int imageid) {
        this.title = title;
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
