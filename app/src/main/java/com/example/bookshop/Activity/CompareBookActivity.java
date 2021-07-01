package com.example.bookshop.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.BookOfferCategoryAdapter;
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

public class CompareBookActivity extends AppCompatActivity {

    Bundle bundle;
    TextView txt_toolbar_book;
    RequestQueue requestQueue;
    List<Book> relateBooks = new ArrayList<>();
    RecyclerView recyclerView;
    BookOfferCategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_book);

        Initialize();
        getSimilarProduct();

     adapter.setListener(book -> {

         Toast.makeText(this, book.getName(), Toast.LENGTH_SHORT).show();

     });

    }

    private void Initialize() {

        bundle = getIntent().getExtras();
        requestQueue = Volley.newRequestQueue(this);

        txt_toolbar_book = findViewById(R.id.compare_book_activity_toolbar_bookName);
        txt_toolbar_book.setText(bundle.getString(Key.BOOK_NAME));

        recyclerView = findViewById(R.id.compare_book_activity_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookOfferCategoryAdapter(this, relateBooks);
        recyclerView.setAdapter(adapter);


    }


    private void getSimilarProduct() {


        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] similarBooks = gson.fromJson(response, Book[].class);

            relateBooks.addAll(Arrays.asList(similarBooks));
            adapter.notifyDataSetChanged();

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