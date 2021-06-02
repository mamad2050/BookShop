package com.example.bookshop.Model;

public class FirstItemOffer {


    private String title, link_img;

    public FirstItemOffer(String title, String link_img) {
        this.title = title;
        this.link_img = link_img;
    }

    public String getTitle() {
        return title;
    }

    public String getLink_img() {
        return link_img;
    }
}
