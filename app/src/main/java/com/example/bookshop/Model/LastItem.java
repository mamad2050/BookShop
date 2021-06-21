package com.example.bookshop.Model;

public class LastItem {

    int title, img_link;

    public LastItem(int title, int img_link) {
        this.title = title;
        this.img_link = img_link;
    }

    public int getTitle() {
        return title;
    }

    public int getImg_link() {
        return img_link;
    }

}
