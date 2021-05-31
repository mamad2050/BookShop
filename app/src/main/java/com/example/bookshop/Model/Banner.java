package com.example.bookshop.Model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Banner {

    private String id;
    private String link_img;


    public Banner(String img_link_banner) {
        this.link_img = img_link_banner;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
