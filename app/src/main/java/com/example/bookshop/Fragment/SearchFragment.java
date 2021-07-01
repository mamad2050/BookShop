package com.example.bookshop.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.BookOfferCategoryAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Book;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    View view;
    RecyclerView recyclerView;
    BookOfferCategoryAdapter adapter;
    List<Book> books = new ArrayList<>();
    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookOfferCategoryAdapter(getContext(), books);
        recyclerView.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(getContext());

        getResponse();


        return view;

    }

    private void getResponse() {


        StringRequest request = new StringRequest(Request.Method.GET, Constants.LINK_GET_ALL_BOOK, response -> {


            Gson gson = new Gson();
            Book[] booksArray = gson.fromJson(response, Book[].class);
            books.addAll(Arrays.asList(booksArray));
            adapter.notifyDataSetChanged();


        }, error -> Log.e(TAG, "getResponse: " + error.getMessage()));

        requestQueue.add(request);


    }
}