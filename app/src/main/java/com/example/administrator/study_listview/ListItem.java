package com.example.administrator.study_listview;

import android.graphics.drawable.Drawable;

public class ListItem {

    private Drawable iconDrawable;
    private String name;
    private String mobile;

    public Drawable getIcon() {
        return iconDrawable;
    }

    public void setIcon(Drawable icon) {
        this.iconDrawable = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
