package com.example.bookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Activity.BookActivity;
import com.example.bookshop.Activity.CartActivity;
import com.example.bookshop.Activity.SearchActivity;
import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Fragment.CategoryFragment;
import com.example.bookshop.Fragment.HomeFragment;
import com.example.bookshop.Fragment.ProfileFragment;
import com.example.bookshop.Fragment.SearchFragment;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.Model.Message;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    public static String defSystemLocale;
    TextView toolbar_title, txt_count;
    ImageView imageView, img_cart;
    ApiInterface request;
    MyPreferencesManager myPreferencesManager;
    String username;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defSystemLocale = Locale.getDefault().getLanguage();
        setContentView(R.layout.activity_home);

        request = ApiClient.getRetrofit().create(ApiInterface.class);
        myPreferencesManager = new MyPreferencesManager(this);

        username = myPreferencesManager.getUserData().get(MyPreferencesManager.USERNAME);

        initialize();
        HomeFragment homeFragment = new HomeFragment();
        setFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private void initialize() {


        txt_count = findViewById(R.id.home_activity_txt_count);
        img_cart = findViewById(R.id.home_activity_cart_img);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar_title = findViewById(R.id.home_activity_toolbar_title);
        toolbar_title.setText(R.string.app_name);
        bottomNavigationView.setItemIconTintList(null);
        imageView = findViewById(R.id.home_img_search);

        imageView.setOnClickListener(event -> {

            startActivity(new Intent(HomeActivity.this, SearchActivity.class));
        });


        img_cart.setOnClickListener(e -> {
            startActivity(new Intent(HomeActivity.this, CartActivity.class));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCountCart(myPreferencesManager.getUserData().get(MyPreferencesManager.USERNAME));
    }

    private void getCountCart(String username) {

        Call<Message> call = request.getCountCart(username);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, retrofit2.Response<Message> response) {

                if (!response.body().getMessage().equals("0")) {
                    txt_count.setVisibility(View.VISIBLE);
                    txt_count.setText(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:

                imageView.setVisibility(View.VISIBLE);
                toolbar_title.setText(R.string.app_name);
                HomeFragment homeFragment = new HomeFragment();
                setFragment(homeFragment);

                break;


            case R.id.nav_categories:

                imageView.setVisibility(View.INVISIBLE);
                toolbar_title.setText(R.string.nav_categories);
                CategoryFragment categoryFragment = new CategoryFragment();
                setFragment(categoryFragment);

                break;

            case R.id.nav_profile:

                imageView.setVisibility(View.INVISIBLE);
                toolbar_title.setText(R.string.nav_profile);
                ProfileFragment profileFragment = new ProfileFragment();
                setFragment(profileFragment);
                break;
        }
        return true;
    }

    private void setFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        defSystemLocale = newConfig.locale.getLanguage();

    }
}