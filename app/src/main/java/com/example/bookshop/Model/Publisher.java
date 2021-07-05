package com.example.bookshop.Model;

public class Publisher {

    private String id ,name,link_img;
    private int color;

    public Publisher(String name, String link_img, int color) {
        this.name = name;
        this.link_img = link_img;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_img() {
        return link_img;
    }

    public String getId() {
        return id;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }
}
