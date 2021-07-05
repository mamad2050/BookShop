package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.AllBookAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Book;
import com.example.bookshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    BottomSheetDialog dialog;
    private static final String TAG = "Search";
    LinearLayout layout_sort, layout_filter;
    RecyclerView recyclerView;
    AllBookAdapter adapter;
    List<Book> books = new ArrayList<>();
    RequestQueue requestQueue;

    public static int SET_CHECK_RDB = 1;

    public static final int RDB_NEW = 1;
    public static final int RDB_PRICE_DESC = 2;
    public static final int RDB_PRICE_ASC = 3;
    public static final int RDB_DISCOUNT = 4;
    public static final int RDB_SOLD = 5;

    RadioButton rdb_new, rdb_sold, rdb_discount, rdb_price_desc, rdb_price_asc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        layout_filter = findViewById(R.id.layout_filter);
        layout_sort = findViewById(R.id.layout_sort);


        recyclerView = findViewById(R.id.recyclerView_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllBookAdapter(this, books);
        recyclerView.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(this);

        books.clear();
        getResponse(Constants.LINK_GET_ALL_BOOK);

        layout_sort.setOnClickListener(v -> {

            dialog = new BottomSheetDialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet_sort, findViewById(R.id.parent));
            dialog.setContentView(view);
            dialog.show();

            rdb_new = view.findViewById(R.id.rdb_new);
            rdb_sold = view.findViewById(R.id.rdb_sold);
            rdb_price_asc = view.findViewById(R.id.rdb_price_asc);
            rdb_price_desc = view.findViewById(R.id.rdb_price_desc);
            rdb_discount = view.findViewById(R.id.rdb_discount);

            switch (SET_CHECK_RDB){

                case RDB_NEW:
                    rdb_new.setChecked(true);
                    break;
                case RDB_PRICE_DESC:
                    rdb_price_desc.setChecked(true);
                    break;
                case RDB_PRICE_ASC:
                    rdb_price_asc.setChecked(true);
                    break;
                case RDB_DISCOUNT:
                    rdb_discount.setChecked(true);
                    break;
                case RDB_SOLD:
                    rdb_sold.setChecked(true);
                    break;
            }

            rdb_new.setOnClickListener(e -> {

                books.clear();
                getResponse(Constants.LINK_GET_ALL_BOOK);
                dialog.dismiss();
               SET_CHECK_RDB = RDB_NEW;


            });

            rdb_discount.setOnClickListener(e -> {

                books.clear();
                getResponse(Constants.SORT_BY_DISCOUNT);
                dialog.dismiss();
                SET_CHECK_RDB = RDB_DISCOUNT;

            });

            rdb_price_desc.setOnClickListener(e -> {

                books.clear();
                getResponse(Constants.SORT_BY_PRICE_DESC);
                dialog.dismiss();
                SET_CHECK_RDB = RDB_PRICE_DESC;

            });

            rdb_price_asc.setOnClickListener(e -> {

                books.clear();
                getResponse(Constants.SORT_BY_PRICE_ASC);
                dialog.dismiss();
                SET_CHECK_RDB = RDB_PRICE_ASC;


            });

            rdb_sold.setOnClickListener(e -> {

                books.clear();
                getResponse(Constants.SORT_BY_SOLD);
                dialog.dismiss();
                SET_CHECK_RDB = RDB_SOLD;

            });


        });


    }

    private void getResponse(String link) {


        StringRequest request = new StringRequest(Request.Method.GET, link, response -> {


            Gson gson = new Gson();
            Book[] booksArray = gson.fromJson(response, Book[].class);
            books.addAll(Arrays.asList(booksArray));
            adapter.notifyDataSetChanged();


        }, error -> Log.e(TAG, "getResponse: " + error.getMessage()));

        requestQueue.add(request);


    }


}