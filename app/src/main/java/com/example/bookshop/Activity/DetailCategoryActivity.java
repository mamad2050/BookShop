package com.example.bookshop.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

    /* all subs recyclerview*/
//    RecyclerView recyclerViewAllSubs;
//    DetailCategoryAdapter detailCategoryAdapter;
//    List<Book> books = new ArrayList<>();

    /* popular books recyclerview*/
    RecyclerView recyclerPopulars;
    BookAdapter bookAdapter;
    List<Book> popularBooks = new ArrayList<>();

    /* new books recyclerview*/
    RecyclerView newBookRecyclerview;
    BookAdapter newBookAdapter;
    List<Book> newBooks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        initialize();
//        getAllSubs();
        getPopulars();
        getNewBooks();

    }


    private void initialize() {


        bundle = getIntent().getExtras();
        title_toolbar = findViewById(R.id.detailCategory_activity_toolbar_txt);
        title_toolbar.setText(bundle.getString(Key.TITLE));

        requestQueue = Volley.newRequestQueue(this);

        /* Initialize all subs recyclerview*/
//        recyclerViewAllSubs = findViewById(R.id.activity_all_category_recyclerview);
//        recyclerViewAllSubs.setHasFixedSize(true);
//        recyclerViewAllSubs.setLayoutManager(new LinearLayoutManager(this));
//        detailCategoryAdapter = new DetailCategoryAdapter(this, books);
//        recyclerViewAllSubs.setAdapter(detailCategoryAdapter);

        /* Initialize populars recyclerview*/
        recyclerPopulars = findViewById(R.id.activity_all_category_popular_recyclerview);
        recyclerPopulars.setHasFixedSize(true);
        recyclerPopulars.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bookAdapter = new BookAdapter(this, popularBooks);
        recyclerPopulars.setAdapter(bookAdapter);

        /* Initialize New recyclerview*/

        newBookRecyclerview =  findViewById(R.id.activity_detail_category_newsRecyclerview);
        newBookRecyclerview.setHasFixedSize(true);
        newBookRecyclerview.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        newBookAdapter = new BookAdapter(this,newBooks);
        newBookRecyclerview.setAdapter(newBookAdapter);




    }


//    private void getAllSubs() {
//
//        Response.Listener<String> listener = response -> {
//
//            Gson gson = new Gson();
//            Book[] booksArray = gson.fromJson(response, Book[].class);
//
//            books.addAll(Arrays.asList(booksArray));
//            detailCategoryAdapter.notifyDataSetChanged();
//
//        };
//
//        Response.ErrorListener errorListener = error -> Log.e(TAG, "getNewBooksResponse: " + error.getMessage());
//
//
//        StringRequest request = new StringRequest(Request.Method.POST, Constants.LINK_DETAIL_CATEGORY, listener, errorListener) {
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//
//                HashMap<String, String> map = new HashMap<>();
//                map.put(Key.ID, bundle.getString(Key.ID));
//
//
//                return map;
//            }
//        };
//
//        requestQueue.add(request);
//
//    }


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
            Book[] populars = gson.fromJson(response, Book[].class);

            newBooks.addAll(Arrays.asList(populars));
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

}