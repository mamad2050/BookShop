package com.example.bookshop.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.BookOfferCategoryAdapter;
import com.example.bookshop.Adapter.DetailCategoryAdapter;
import com.example.bookshop.Adapter.BookAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.Book;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailCategoryActivity extends AppCompatActivity {

    private static final String TAG = "DetailCategoryActivity";

    RequestQueue requestQueue;
    /*Toolbar*/
    TextView title_toolbar;
    Bundle bundle;


    /* popular books recyclerview*/
    RecyclerView recyclerPopulars;
    BookAdapter bookAdapter;
    List<Book> popularBooks = new ArrayList<>();

    /* new books recyclerview*/
    RecyclerView newBookRecyclerview;
    BookAdapter newBookAdapter;
    List<Book> newBooks = new ArrayList<>();

    /* offer books recyclerview*/
    RecyclerView offerRecyclerView;
    BookOfferCategoryAdapter bookOfferCategoryAdapter;
    List<Book> offBooks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        initialize();
//        getAllSubs();
        getPopulars();
        getNewBooks();
        getOfferBook();

        bookOfferCategoryAdapter.setListener(book -> {
            Intent intent = new Intent(DetailCategoryActivity.this, BookActivity.class);
            intent.putExtra(Key.ID, book.getId());
            intent.putExtra(Key.CATEGORY_ID, book.getCategory_id());
            startActivity(intent);
        });


    }


    private void initialize() {


        bundle = getIntent().getExtras();
        title_toolbar = findViewById(R.id.detailCategory_activity_toolbar_txt);
        title_toolbar.setText(bundle.getString(Key.TITLE));

        requestQueue = Volley.newRequestQueue(this);

        /* Initialize all subs recyclerview*/


        /* Initialize populars recyclerview*/
        recyclerPopulars = findViewById(R.id.activity_all_category_popular_recyclerview);
        recyclerPopulars.setHasFixedSize(true);
        recyclerPopulars.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookAdapter(this, popularBooks);
        recyclerPopulars.setAdapter(bookAdapter);

        /* Initialize New recyclerview*/

        newBookRecyclerview = findViewById(R.id.activity_detail_category_newsRecyclerview);
        newBookRecyclerview.setHasFixedSize(true);
        newBookRecyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        newBookAdapter = new BookAdapter(this, newBooks);
        newBookRecyclerview.setAdapter(newBookAdapter);


        /* Initialize Offer recyclerview*/

        offerRecyclerView = findViewById(R.id.activity_detail_category_offRecyclerview);
        offerRecyclerView.setHasFixedSize(true);
        offerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookOfferCategoryAdapter = new BookOfferCategoryAdapter(this, offBooks);
        offerRecyclerView.setAdapter(bookOfferCategoryAdapter);


    }


    private void getPopulars() {

        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] populars = gson.fromJson(response, Book[].class);

            popularBooks.addAll(Arrays.asList(populars));
            bookAdapter.notifyDataSetChanged();



        };

        Response.ErrorListener errorListener = error -> Log.e(TAG, "getNewBooksResponse: " + error.getMessage());


        StringRequest request = new StringRequest(Request.Method.POST, Constants.LINK_POPULARS, listener, errorListener) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String, String> map = new HashMap<>();
                map.put(Key.ID, bundle.getString(Key.ID));


                return map;
            }
        };

        requestQueue.add(request);

    }


    private void getNewBooks() {

        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] news = gson.fromJson(response, Book[].class);

            newBooks.addAll(Arrays.asList(news));
            newBookAdapter.notifyDataSetChanged();

        };

        Response.ErrorListener errorListener = error -> Log.e(TAG, "getNewBooksResponse: " + error.getMessage());


        StringRequest request = new StringRequest(Request.Method.POST, Constants.LINK_NEWS_FOR_CATEGORY, listener, errorListener) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String, String> map = new HashMap<>();
                map.put(Key.ID, bundle.getString(Key.ID));

                return map;
            }
        };

        requestQueue.add(request);

    }


    private void getOfferBook() {

        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] offers = gson.fromJson(response, Book[].class);

            offBooks.addAll(Arrays.asList(offers));
            bookOfferCategoryAdapter.notifyDataSetChanged();

        };

        Response.ErrorListener errorListener = error -> Log.e(TAG, "getNewBooksResponse: " + error.getMessage());


        StringRequest request = new StringRequest(Request.Method.POST, Constants.LINK_OFFER_CATEGORY, listener, errorListener) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String, String> map = new HashMap<>();
                map.put(Key.ID, bundle.getString(Key.ID));

                return map;
            }
        };

        requestQueue.add(request);

    }

}