package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Adapter.CartAdapter;
import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Global.DecimalFormatter;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.Model.Cart;
import com.example.bookshop.Model.Message;
import com.example.bookshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Cart> carts = new ArrayList<>();
    CartAdapter cartAdapter;
    ApiInterface request;
    MyPreferencesManager myPreferencesManager;
    String username;
    Button btn_next;

    BottomSheetDialog bottomSheetDialog;

    private int TOTAL, COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        request = ApiClient.getRetrofit().create(ApiInterface.class);

        myPreferencesManager = new MyPreferencesManager(this);
        username = myPreferencesManager.getUserData().get(MyPreferencesManager.USERNAME);

        btn_next=findViewById(R.id.card_activity_btn_next);


        recyclerView = findViewById(R.id.activity_cart_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Cart>> call = request.getListCart(username);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                carts = response.body();
                cartAdapter = new CartAdapter(CartActivity.this, carts, cart -> {


                    request = ApiClient.getRetrofit().create(ApiInterface.class);

                    Call<Message> deleteCall = request.deleteCart(cart.getCart_id());
                    deleteCall.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {

                            if (response.body().getMessage().equals("Ok")) {
                                cartAdapter.getCartForDelete(cart);
                                Toast.makeText(CartActivity.this, "حذف شد", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }, (price, count) -> {


                    TOTAL += Integer.parseInt(price);
                    COUNT = Integer.parseInt(count);

                });
                recyclerView.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



        btn_next.setOnClickListener(v -> {

            bottomSheetDialog = new BottomSheetDialog(CartActivity.this);
            View layout_bottomSheet = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet_cart,
                    findViewById(R.id.parent_bottomSheet), false);

            Button btn_bottom_sheet = layout_bottomSheet.findViewById(R.id.btn_buy);
            TextView txt_total_bottom_sheet = layout_bottomSheet.findViewById(R.id.txt_total_price);
            TextView txt_count_bottom_sheet = layout_bottomSheet.findViewById(R.id.txt_all_product_size);

            txt_count_bottom_sheet.setText(DecimalFormatter.convert(COUNT));
            txt_total_bottom_sheet.setText(DecimalFormatter.convert(TOTAL));
//
            btn_bottom_sheet.setOnClickListener(e -> {

                Intent intent = new Intent(CartActivity.this, AddressActivity.class);
                startActivity(intent);

            });

            bottomSheetDialog.setContentView(layout_bottomSheet);
            bottomSheetDialog.show();

        });
    }
}