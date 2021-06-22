package com.example.bookshop.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.bookshop.Adapter.BookNewAdapter;
import com.example.bookshop.Adapter.BookOfferAdapter;
import com.example.bookshop.Adapter.CategoryAdapter;
import com.example.bookshop.Adapter.PublisherAdapter;
import com.example.bookshop.Adapter.SecondBannerAdapter;
import com.example.bookshop.Adapter.SliderAdapter;
import com.example.bookshop.HomeActivity;
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.Model.LastItem;
import com.example.bookshop.Model.Offer;
import com.example.bookshop.Model.Banner;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Category;
import com.example.bookshop.Model.FirstItemOffer;
import com.example.bookshop.Model.Publisher;
import com.example.bookshop.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

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


    /*SecondBanner*/
    List<Banner> secondBanners = new ArrayList<>();
    SecondBannerAdapter secondBannerAdapter;
    RecyclerView recyclerViewSecondBanner;

    /*Book News*/
    List<Offer> listNews = new ArrayList<>();
    BookNewAdapter bookNewAdapter;
    RecyclerView recyclerViewNewBooks;

    /*Publishers*/
    List<Publisher> publishers = new ArrayList<>();
    PublisherAdapter publisherAdapter;
    RecyclerView recyclerViewPublishers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initialize();
        getBannersResponse();
        getCategoriesResponse();
        getBookOfferResponse();
        getSecondBannerResponse();
        getNewBooksResponse();
        getPublisherResponse();

        return view;
    }


    private void initialize() {

        requestQueue = Volley.newRequestQueue(getContext());

        /*Initialize Slider View Pager*/
        slider = view.findViewById(R.id.viewPager);
        tabs = view.findViewById(R.id.tabs);
        sliderAdapter = new SliderAdapter(getContext(), banners);
        slider.setAdapter(sliderAdapter);
        if (HomeActivity.defSystemLocale.equals("fa")) {
            slider.setRotationY(180);
        }
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
        FirstItemOffer firstItemOffer = new FirstItemOffer("محصولات تخفیف دار فروشگاه", "http://192.168.1.165/book%20store/icons/offer.png");
        listOffer.add(new Offer(1, firstItemOffer));
        bookOfferAdapter = new BookOfferAdapter(getContext(), listOffer);
        recyclerViewBookOffer.setAdapter(bookOfferAdapter);


        /*Initialize Second Banner Recycler View */
        recyclerViewSecondBanner = view.findViewById(R.id.homeFragment_recyclerView_second_banner);
        recyclerViewSecondBanner.setLayoutManager(new GridLayoutManager(getContext(), 2));
        secondBannerAdapter = new SecondBannerAdapter(getContext(), secondBanners);
        recyclerViewSecondBanner.setHasFixedSize(true);
        recyclerViewSecondBanner.setAdapter(secondBannerAdapter);


        /*Initialize Book News RecyclerView*/
        recyclerViewNewBooks = view.findViewById(R.id.homeFragment_recyclerView_news);
        recyclerViewNewBooks.setHasFixedSize(true);
        recyclerViewNewBooks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        /*InitializeFirst Object Item For Book News Recycler View*/
        FirstItemOffer firstItemNews = new FirstItemOffer("جدیدترین محصولات فروشگاه", "http://192.168.1.165/book%20store/icons/new.png");
        listNews.add(new Offer(1, firstItemNews));

        bookNewAdapter = new BookNewAdapter(getContext(), listNews);
        recyclerViewNewBooks.setAdapter(bookNewAdapter);



        /*Initialize Publisher RecyclerView*/
        recyclerViewPublishers = view.findViewById(R.id.homeFragment_recyclerView_publisher);
        recyclerViewPublishers.setHasFixedSize(true);
        recyclerViewPublishers.setLayoutManager(new GridLayoutManager(getContext(), 3));
        publisherAdapter = new PublisherAdapter(publishers, getContext());
        recyclerViewPublishers.setAdapter(publisherAdapter);

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

    private void getBookOfferResponse() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LINK_BOOK_OFFER, response -> {
            Gson gson = new Gson();
            BookOffer[] arrayBookOffer = gson.fromJson(response, BookOffer[].class);

            for (BookOffer bookOffer : arrayBookOffer) {
                listOffer.add(new Offer(0, bookOffer));
            }
            bookOfferAdapter.notifyDataSetChanged();
            /*last item for new books recyclerview*/
            LastItem lastItem = new LastItem(R.string.show_all, R.drawable.forward);
            listOffer.add(new Offer(2, lastItem));
            /*                               */


        }, error -> Log.e(TAG, "onErrorResponse: " + error.getMessage()));

        requestQueue.add(stringRequest);

    }

    private void getSecondBannerResponse() {

        StringRequest request = new StringRequest(Request.Method.GET, Constants.LINK_BANNER_SECOND, response -> {

            Gson gson = new Gson();
            Banner[] secondBannerArray = gson.fromJson(response, Banner[].class);
            secondBanners.addAll(Arrays.asList(secondBannerArray));
            secondBannerAdapter.notifyDataSetChanged();

        }, error -> Log.e(TAG, "getSecondBannerResponse: " + error.getMessage()));

        requestQueue.add(request);

    }

    private void getNewBooksResponse() {

        StringRequest request = new StringRequest(Request.Method.GET, Constants.LINK_BOOK_NEWS, response -> {

            Gson gson = new Gson();
            BookOffer[] bookNewsArray = gson.fromJson(response, BookOffer[].class);

            for (BookOffer bookOffer : bookNewsArray) {
                listNews.add(new Offer(0, bookOffer));
            }
            bookNewAdapter.notifyDataSetChanged();

            /*last item for new books recyclerview*/
            LastItem lastItem = new LastItem(R.string.show_all, R.drawable.forward);
            listNews.add(new Offer(2, lastItem));
            /*                               */

        }, error -> Log.e(TAG, "getNewBooksResponse: " + error.getMessage()));

        requestQueue.add(request);

    }


    private void getPublisherResponse() {

        StringRequest request = new StringRequest(Request.Method.GET, Constants.LINK_PUBLISHERS, response -> {

            Gson gson = new Gson();
            Publisher[] publisherArray = gson.fromJson(response, Publisher[].class);
            publishers.addAll(Arrays.asList(publisherArray));
            publisherAdapter.notifyDataSetChanged();


        }, error -> Log.e(TAG, "getPublisherResponse:" + error.getMessage()));

        requestQueue.add(request);

    }


}