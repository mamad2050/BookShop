package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Global.DecimalFormatter;
import com.example.bookshop.Global.Key;
import com.example.bookshop.R;

public class BookActivity extends AppCompatActivity {

    Bundle bundle;
    ImageView book_img;
    TextView txt_name;
    TextView txt_name2;
    TextView txt_author;
    TextView txt_author2;
    TextView txt_final_price;
    TextView txt_price;
    TextView txt_genre;
    TextView txt_discount;
    TextView txt_pages;
    TextView txt_sold;
    TextView txt_publish_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bundle = getIntent().getExtras();
        book_img = findViewById(R.id.book_activity_book_img);
        txt_name = findViewById(R.id.book_activity_book_name);
        txt_name2 = findViewById(R.id.book_activity_book_name2);
        txt_author = findViewById(R.id.book_activity_book_author);
        txt_author2 = findViewById(R.id.book_activity_book_author2);
        txt_final_price = findViewById(R.id.book_activity_book_final_price);
        txt_price = findViewById(R.id.book_activity_book_price);
        txt_pages = findViewById(R.id.book_activity_book_pages);
        txt_sold = findViewById(R.id.book_activity_book_sold);
        txt_publish_date = findViewById(R.id.book_activity_book_date);
        txt_genre = findViewById(R.id.book_activity_book_genre);
        txt_discount = findViewById(R.id.activity_book_discount);


        Glide.with(this).load(bundle.getString(Key.IMG)).into(book_img);
        txt_name.setText(bundle.getString(Key.TITLE));
        txt_name2.setText(bundle.getString(Key.TITLE));
        txt_author.setText(bundle.getString(Key.AUTHOR));
        txt_author2.setText(bundle.getString(Key.AUTHOR));
        txt_genre.setText(bundle.getString(Key.GENRE));



        int pages = Integer.parseInt(bundle.getString(Key.PAGES));
        txt_pages.setText(DecimalFormatter.formatted(pages));



        String date = bundle.getString(Key.PUBLISH);
        txt_publish_date.setText(date);


        int sold = Integer.parseInt(bundle.getString(Key.SOLD));
        txt_sold.setText(DecimalFormatter.formatted(sold));


        int price = Integer.parseInt(bundle.getString(Key.PRICE));
        SpannableString spannableString = new SpannableString(DecimalFormatter.formatted(price));
        spannableString.setSpan(new StrikethroughSpan(), 0, bundle.getString(Key.PRICE).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_price.setText(spannableString);


        int finalPrice = Integer.parseInt(bundle.getString(Key.FINAL_PRICE));
        txt_final_price.setText(DecimalFormatter.formatted(finalPrice));


        int discount = Integer.parseInt(bundle.getString(Key.DISCOUNT));
        txt_discount.setText(DecimalFormatter.formatted(discount)+"%");



    }
}