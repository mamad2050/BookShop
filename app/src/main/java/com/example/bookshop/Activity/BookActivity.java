package com.example.bookshop.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bookshop.Adapter.BookAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Global.DecimalFormatter;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.Book;
import com.example.bookshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookActivity extends AppCompatActivity {


    private static final String TAG = "BookActivity";

    Bundle bundle;
    Book book;

    RequestQueue requestQueue;

    ImageView book_img;
    TextView txt_name;
    TextView txt_name_table;
    TextView txt_author;
    TextView txt_author_table;
    TextView txt_final_price;
    TextView txt_price;
    TextView txt_genre;
    TextView txt_discount;
    TextView txt_pages;
    TextView txt_sold;
    TextView txt_publish_date;
    TextView txt_description;
    LinearLayout discount_parent;

    /*relate books recyclerView*/

    RecyclerView relatesRecyclerView;
    BookAdapter bookAdapter;
    List<Book> relateBooks = new ArrayList<>();

    /*ToolBar Options */

    ImageView img_back;
    ImageView img_more;
    ImageView img_like;
    ImageView img_cart;

    /*Bottom Sheet*/

    TextView txt_compare_bottomSheet;
    TextView txt_share_bottomSheet;
    BottomSheetDialog bottomSheetDialog;
    View layout_bottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initialize();

        getBookResponse();
        getSimilarProduct();

        img_more.setOnClickListener(v -> {

            bottomSheetDialog = new BottomSheetDialog(BookActivity.this);
            layout_bottomSheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,
                    findViewById(R.id.parent_bottomSheet), false);

            txt_compare_bottomSheet = layout_bottomSheet.findViewById(R.id.bottom_sheet_compare);
            txt_share_bottomSheet = layout_bottomSheet.findViewById(R.id.bottom_sheet_share);


            bottomSheetDialog.setContentView(layout_bottomSheet);
            bottomSheetDialog.show();

        });

    }


    private void initialize() {


        requestQueue = Volley.newRequestQueue(this);

        bundle = getIntent().getExtras();

        book_img = findViewById(R.id.book_activity_book_img);
        txt_name = findViewById(R.id.book_activity_book_name);
        txt_name_table = findViewById(R.id.book_activity_book_name2);
        txt_author = findViewById(R.id.book_activity_book_author);
        txt_author_table = findViewById(R.id.book_activity_book_author2);
        txt_final_price = findViewById(R.id.book_activity_book_final_price);
        txt_price = findViewById(R.id.book_activity_book_price);
        txt_pages = findViewById(R.id.book_activity_book_pages);
        txt_sold = findViewById(R.id.book_activity_book_sold);
        txt_publish_date = findViewById(R.id.book_activity_book_date);
        txt_genre = findViewById(R.id.book_activity_book_genre);
        txt_discount = findViewById(R.id.activity_book_discount);
        txt_description = findViewById(R.id.book_activity_description);
        discount_parent = findViewById(R.id.book_activity_discount_parent);

        /*Initialize Relate Books recyclerView*/
        relatesRecyclerView = findViewById(R.id.book_activity_relates_recyclerview);
        relatesRecyclerView.setHasFixedSize(true);
        relatesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookAdapter(this, relateBooks);
        relatesRecyclerView.setAdapter(bookAdapter);

        /*Initializing Toolbar Options*/

        img_more = findViewById(R.id.book_activity_more_img);
        img_cart = findViewById(R.id.book_activity_cart_img);
        img_like = findViewById(R.id.book_activity_like_img);
        img_back = findViewById(R.id.book_activity_back_img);

    }

    private void getBookResponse() {


        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] arrayBook = gson.fromJson(response, Book[].class);
            book = arrayBook[0];

            setFields();


        };

        Response.ErrorListener errorListener = error -> Log.e(TAG, "onErrorResponse: " + error.getMessage());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LINK_GET_BOOK, listener, errorListener) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Key.ID, bundle.getString(Key.ID));
                return hashMap;
            }

        };
        requestQueue.add(stringRequest);

    }

    private void setFields() {

        txt_name.setText(book.getName());
        Glide.with(this).load(book.getLink_img()).into(book_img);
        txt_name.setText(book.getName());
        txt_name_table.setText(book.getName());
        txt_author.setText(book.getAuthor());
        txt_author_table.setText(book.getAuthor());
        txt_genre.setText(book.getGenre());
        txt_description.setText(book.getDescription());

        int pages = Integer.parseInt(book.getPages());
        txt_pages.setText(DecimalFormatter.convert(pages));

        int date = Integer.parseInt(book.getPublish_date());
        txt_publish_date.setText(DecimalFormatter.convert2(date));

        int sold = Integer.parseInt(book.getSold());
        txt_sold.setText(new StringBuilder(DecimalFormatter.convert(sold) + " نسخه"));


        int finalPrice = Integer.parseInt(book.getFinal_price());
        txt_final_price.setText(DecimalFormatter.convert(finalPrice));


        int discount = Integer.parseInt(book.getDiscount());

        if (discount == 0) {
            discount_parent.setVisibility(View.GONE);
        } else {
            txt_discount.setText(new StringBuilder(DecimalFormatter.convert(discount) + "%"));

            int price = Integer.parseInt(book.getPrice());

            SpannableString spannableString = new SpannableString(DecimalFormatter.convert(price));
            spannableString.setSpan(new StrikethroughSpan(), 0, book.getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_price.setText(spannableString);

        }

    }


    private void getSimilarProduct() {


        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] similarBooks = gson.fromJson(response, Book[].class);

            relateBooks.addAll(Arrays.asList(similarBooks));
            bookAdapter.notifyDataSetChanged();

        };

        Response.ErrorListener errorListener = error -> {

            Log.d("Error : ", error.getMessage() + "");

        };

        StringRequest request = new StringRequest(Request.Method.POST, Constants.LINK_RELATE_BOOKS, listener, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put(Key.CATEGORY_ID, bundle.getString(Key.CATEGORY_ID));
                return map;

            }
        };
        requestQueue.add(request);

    }

}