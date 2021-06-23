package com.example.bookshop.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.AllCategoryAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Category;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CategoryFragment extends Fragment {

    private static final String TAG = "CategoryFragment";
    View view;
    RequestQueue requestQueue;
    AllCategoryAdapter allCategoryAdapter;
    RecyclerView recyclerView;
    List<Category> categories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        initialize();
        getAllCategoryResponse();


        return view;
    }

    private void initialize() {


        requestQueue = Volley.newRequestQueue(getContext());

        /*Initialize Recycler View*/
        allCategoryAdapter = new AllCategoryAdapter(getContext(), categories);
        recyclerView = view.findViewById(R.id.categoryFragment_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(allCategoryAdapter);

    }

    private void getAllCategoryResponse() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LINK_ALL_CATEGORY, response -> {

            Gson gson = new Gson();
            Category[] allCategoryArray = gson.fromJson(response,Category[].class);
            categories.addAll(Arrays.asList(allCategoryArray));
            allCategoryAdapter.notifyDataSetChanged();

        }, error -> Log.e(TAG, "getAllCategoryResponse: " + error.getMessage()));

        requestQueue.add(stringRequest);
    }
}