package com.example.bookshop.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.SliderAdapter;
import com.example.bookshop.Banner.Banner;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "Error";
    View view;
    List<Banner> banners = new ArrayList<>();
    SliderAdapter sliderAdapter;
    ViewPager slider;
    TabLayout tabs;
    final int paddingPx = 25;
    final float MIN_SCALE = 0.8f;
    final float MAX_SCALE = 1f;


    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initialize();
        getBanner_response();

        return view;
    }

    private void initialize() {

        requestQueue = Volley.newRequestQueue(getContext());

        slider = view.findViewById(R.id.viewPager);
        tabs = view.findViewById(R.id.tabs);
        sliderAdapter = new SliderAdapter(getContext(), banners);
        slider.setAdapter(sliderAdapter);
        slider.setRotationY(180);
        tabs.setupWithViewPager(slider, true);


        setAnimationForSlider();

    }

    private void getBanner_response() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LINK_BANNER_SLIDER, response -> {
            Gson gson = new Gson();
            Banner[] arrayBanners = gson.fromJson(response, Banner[].class);

            banners.addAll(Arrays.asList(arrayBanners));
            sliderAdapter.notifyDataSetChanged();

        }, error -> Log.e(TAG, "onErrorResponse: " + error.getMessage()));

        requestQueue.add(stringRequest);
    }



    private void setAnimationForSlider() {

        slider.setClipToPadding(false);
        slider.setPadding(paddingPx, 0, paddingPx, 0);


        ViewPager.PageTransformer transformer = (page, position) -> {
            float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
            float pageWidthPx = pagerWidthPx - 2 * paddingPx;

            float maxVisiblePages = pagerWidthPx / pageWidthPx;
            float center = maxVisiblePages / 2f;

            float scale;
            if (position + 0.5f < center - 0.5f || position > center) {
                scale = MIN_SCALE;
            } else {
                float coef;
                if (position + 0.5f < center) {
                    coef = (position + 1 - center) / 0.5f;
                } else {
                    coef = (center - position) / 0.5f;
                }
                scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
            }
            page.setScaleX(scale);
            page.setScaleY(scale);
        };

        slider.setPageTransformer(false, transformer);
    }

}