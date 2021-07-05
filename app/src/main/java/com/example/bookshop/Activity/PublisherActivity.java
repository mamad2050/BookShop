package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.bookshop.Adapter.AllBookAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.Book;
import com.example.bookshop.Model.Publisher;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublisherActivity extends AppCompatActivity {

    Bundle bundle;
    TextView txt_name_publisher;
    RequestQueue requestQueue;
    List<Book> relateBooks = new ArrayList<>();
    RecyclerView recyclerView;
    AllBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);

        bundle = getIntent().getExtras();
        txt_name_publisher = findViewById(R.id.publisher_ctivity_txt_name);

        txt_name_publisher.setText(bundle.getString(Key.PUBLISHER_NAME));
//        Toast.makeText(this, bundle.getString(Key.PUBLISHER_ID), Toast.LENGTH_SHORT).show();

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.publisher_activity_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllBookAdapter(this, relateBooks);
        recyclerView.setAdapter(adapter);

        getBooks();

        adapter.setListener(book -> {

            Intent intent = new Intent(PublisherActivity.this, BookActivity.class);
            intent.putExtra(Key.CATEGORY_ID, book.getCategory_id());
            intent.putExtra(Key.ID, book.getId());
            startActivity(intent);


        });


    }


    private void getBooks() {


        Response.Listener<String> listener = response -> {

            Gson gson = new Gson();
            Book[] BooksArray = gson.fromJson(response, Book[].class);

            relateBooks.addAll(Arrays.asList(BooksArray));
            adapter.notifyDataSetChanged();

        };

        Response.ErrorListener errorListener = error -> Log.d("Error : ", error.getMessage() + "");

        StringRequest request = new StringRequest(Request.Method.POST, Constants.GET_BOOK_PUBLISHER, listener, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put(Key.PUBLISHER_ID, bundle.getString(Key.PUBLISHER_ID));
                return map;

            }
        };
        requestQueue.add(request);

    }
}