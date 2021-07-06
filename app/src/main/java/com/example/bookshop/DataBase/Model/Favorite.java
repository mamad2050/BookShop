package com.example.bookshop.DataBase.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "Favorite")
public class Favorite {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "id_book")
    public String id_book;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category_id")
    public String category_id;

    @ColumnInfo(name = "link_img")
    public String link_img;

    @ColumnInfo(name = "price")
    public String price;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "discount")
    public String discount;

    @ColumnInfo(name = "final_price")
    public String final_price;

    @ColumnInfo(name = "add_to_favorite")
    public int add_to_favorite;





}
