package com.example.bookshop.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.BookNewAdapter;
import com.example.bookshop.Adapter.DetailCategoryAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.Model.LastItem;
import com.example.bookshop.Model.Offer;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailCategoryActivity extends AppCompatActivity {

    private static final String TAG = "DetailCategoryActivity";
    TextView title_toolbar;
    Bundle bundle;
    RecyclerView recyclerView;
    DetailCategoryAdapter detailCategoryAdapter;
    RequestQueue requestQueue;
    List<BookOffer> books = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        initialize();
        bundle = getIntent().getExtras();
getNewBooksResponse();
        title_toolbar = findViewById(R.id.detailCategory_activity_toolbar_txt);
        title_toolbar.setText(bundle.getString(Key.TITLE));


    }

    private void initialize() {

        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.activity_all_category_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailCategoryAdapter = new DetailCategoryAdapter(this, books);
        recyclerView.setAdapter(detailCategoryAdapter);

    }


    private void getNewBooksResponse() {





        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            BookOffer[] booksArray = gson.fromJson(response, BookOffer[].class);

            books.addAll(Arrays.asList(booksArray));
            detailCategoryAdapter.notifyDataSetChanged();

        };

        Response.ErrorListener errorListener = error -> Log.e(TAG, "getNewBooksResponse: " + error.getMessage());


        StringRequest request = new StringRequest(Request.Method.POST, Constants.LINK_DETAIL_CATEGORY, listener, errorListener){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String,String>map = new HashMap<>();
                map.put(Key.ID,bundle.getString(Key.ID));


                return map;
            }
        };

        requestQueue.add(request);

    }
}