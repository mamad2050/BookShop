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

import com.example.bookshop.Activity.SearchActivity;
import com.example.bookshop.Fragment.CategoryFragment;
import com.example.bookshop.Fragment.HomeFragment;
import com.example.bookshop.Fragment.ProfileFragment;
import com.example.bookshop.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    public static String defSystemLocale;
    TextView toolbar_title;
    ImageView imageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defSystemLocale = Locale.getDefault().getLanguage();
        setContentView(R.layout.activity_home);

        initialize();
        HomeFragment homeFragment = new HomeFragment();
        setFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private void initialize() {

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar_title = findViewById(R.id.home_activity_toolbar_title);
        toolbar_title.setText(R.string.app_name);
        bottomNavigationView.setItemIconTintList(null);
        imageView = findViewById(R.id.home_img_search);

        imageView.setOnClickListener(event -> {

            startActivity(new Intent(HomeActivity.this, SearchActivity.class));
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