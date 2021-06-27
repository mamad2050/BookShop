package com.example.bookshop.Model;

public class Book {

    private String id, category_id, name, link_img, price, discount, brand, final_price, author, pages, publish_date, sold, genre;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
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


    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getBrand() {
        return brand;
    }

    public String getFinal_price() {
        return final_price;
    }

    public String getAuthor() {
        return author;
    }

    public String getPages() {
        return pages;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getSold() {
        return sold;
    }

    public String getGenre() {
        return genre;
    }

}
