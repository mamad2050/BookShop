package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.IntroAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Intro;
import com.example.bookshop.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private static final String TAG = "lottie error";
    ViewPager viewPager;
    TabLayout tabs;
    Button btn_go;
    RequestQueue requestQueue;
    List<Intro> introList = new ArrayList<>();
    IntroAdapter introAdapter;
    TextView txt_before, txt_after;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        initialize();

        getIntros();


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == introList.size() - 1) {

                    tabs.setVisibility(View.INVISIBLE);
                    btn_go.setVisibility(View.VISIBLE);
                    txt_after.setText("رد شدن");


                } else {

                    tabs.setVisibility(View.VISIBLE);
                    btn_go.setVisibility(View.INVISIBLE);
                    txt_after.setText("بعدی");

                }

                if (tab.getPosition() > 0) {

                    txt_before.setVisibility(View.VISIBLE);
                }else {
                    txt_before.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btn_go.setOnClickListener( e -> startActivity(new Intent(IntroActivity.this,LoginActivity.class)));



    }

    private void getIntros() {

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_INTRO, response -> {

            Gson gson = new Gson();
            Intro[] introArray = gson.fromJson(response, Intro[].class);
            introList.addAll(Arrays.asList(introArray));
            introAdapter.notifyDataSetChanged();

        }, error -> Log.e(TAG, "onCreate: " + error.getMessage()));

        requestQueue.add(request);

    }

    private void initialize() {

        btn_go = findViewById(R.id.intro_btn_login);
        viewPager = findViewById(R.id.intro_viewpager);
        txt_after = findViewById(R.id.intro_txt_after);
        txt_before = findViewById(R.id.intro_txt_before);
        tabs = findViewById(R.id.intro_tabs);
        requestQueue = Volley.newRequestQueue(this);
        introAdapter = new IntroAdapter(this, introList);
        viewPager.setAdapter(introAdapter);
        tabs.setupWithViewPager(viewPager);

        viewPager.setRotationY(180);
    }
}