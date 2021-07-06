package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bookshop.Adapter.CartAdapter;
import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.Model.Cart;
import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cart> carts= new ArrayList<>();
    CartAdapter cartAdapter;
    ApiInterface request;
    MyPreferencesManager myPreferencesManager;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        request = ApiClient.getRetrofit().create(ApiInterface.class);

        myPreferencesManager = new MyPreferencesManager(this);
        username = myPreferencesManager.getUserData().get(MyPreferencesManager.USERNAME);


        recyclerView = findViewById(R.id.activity_cart_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Cart>> call = request.getListCart(username);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                carts = response.body();
                cartAdapter = new CartAdapter(CartActivity.this,carts);
                recyclerView.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }
}