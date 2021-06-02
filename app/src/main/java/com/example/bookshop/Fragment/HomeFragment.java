package com.example.bookshop.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.BookOfferAdapter;
import com.example.bookshop.Adapter.CategoryAdapter;
import com.example.bookshop.Adapter.SliderAdapter;
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.Model.Offer;
import com.example.bookshop.Model.Banner;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Category;
import com.example.bookshop.Model.FirstItemOffer;
import com.example.bookshop.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "Error";
    RequestQueue requestQueue;
    View view;

    /*Slider*/
    List<Banner> banners = new ArrayList<>();
    SliderAdapter sliderAdapter;
    ViewPager slider;
    TabLayout tabs;


    /*Category*/
    List<Category> categories = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerViewCategory;

    /*Book Offer*/
    List<Offer> listOffer = new ArrayList<>();
    BookOfferAdapter bookOfferAdapter;
    RecyclerView recyclerViewBookOffer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initialize();
        getBannersResponse();
        getCategoriesResponse();
        getBookOfferResponse();

        return view;
    }

    private void initialize() {

        requestQueue = Volley.newRequestQueue(getContext());

        /*Initialize Slider View Pager*/
        slider = view.findViewById(R.id.viewPager);
        tabs = view.findViewById(R.id.tabs);
        sliderAdapter = new SliderAdapter(getContext(), banners);
        slider.setAdapter(sliderAdapter);
        tabs.setupWithViewPager(slider, true);

        /*Initialize Category Recycler View*/
        recyclerViewCategory = view.findViewById(R.id.recyclerView_category);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        recyclerViewCategory.setAdapter(categoryAdapter);


        /*Initialize Book Offer RecyclerView*/
        recyclerViewBookOffer = view.findViewById(R.id.homeFragment_recyclerView_bookOffer);
        recyclerViewBookOffer.setHasFixedSize(true);
        recyclerViewBookOffer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        /*First Object Item For Book Offer Recycler View*/
        FirstItemOffer firstItemOffer = new FirstItemOffer( "مشاهده همه >", "https://www.digikala.com/static/files/63494995.png");
        listOffer.add(new Offer(1, firstItemOffer));

        bookOfferAdapter = new BookOfferAdapter(getContext(), listOffer);
        recyclerViewBookOffer.setAdapter(bookOfferAdapter);

    }

    private void getCategoriesResponse() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LINK_CATEGORY,
                response -> {

                    Gson gson = new Gson();
                    Category[] arrayCategories = gson.fromJson(response, Category[].class);
                    categories.addAll(Arrays.asList(arrayCategories));
                    categoryAdapter.notifyDataSetChanged();


                }, error -> Log.e(TAG, "getCategories: " + error.getMessage()));

        requestQueue.add(stringRequest);

    }

    private void getBannersResponse() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LINK_BANNER_SLIDER, response -> {
            Gson gson = new Gson();
            Banner[] arrayBanners = gson.fromJson(response, Banner[].class);

            banners.addAll(Arrays.asList(arrayBanners));
            sliderAdapter.notifyDataSetChanged();

        }, error -> Log.e(TAG, "onErrorResponse: " + error.getMessage()));

        requestQueue.add(stringRequest);

        setAnimationForSlider();


    }

    private void setAnimationForSlider() {
        final int paddingPx = 180;
        final float MIN_SCALE = 0.8f;
        final float MAX_SCALE = 1f;
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

        setThreadForSlider();

    }

    private void setThreadForSlider() {

        final boolean running_thread = true;
        final int sleepDuration = 4000;

        Thread thread = new Thread() {

            @Override
            public void run() {

                while (running_thread) {

                    try {
                        Thread.sleep(sleepDuration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (getActivity() == null)
                        return;


                    getActivity().runOnUiThread(() -> {

                        if (slider.getCurrentItem() < banners.size() - 1)
                            slider.setCurrentItem(slider.getCurrentItem() + 1);

                        else
                            slider.setCurrentItem(0);
                    });
                }

            }
        };
        thread.start();
    }

    private void getBookOfferResponse(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LINK_BOOK_OFFER, response -> {
            Gson gson = new Gson();
            BookOffer[] arrayBookOffer = gson.fromJson(response, BookOffer[].class);

            for (BookOffer bookOffer : arrayBookOffer) {
                listOffer.add(new Offer(0, (BookOffer) bookOffer));
            }
            bookOfferAdapter.notifyDataSetChanged();

        }, error -> Log.e(TAG, "onErrorResponse: " + error.getMessage()));

        requestQueue.add(stringRequest);


    }

}