package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bookshop.HomeActivity;
import com.example.bookshop.Model.Banner;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    List<Banner> banners;

    public SliderAdapter(Context context, List<Banner> banners) {
        this.context = context;
        this.banners = banners;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_slider, container, false);
        ImageView img_slider = view.findViewById(R.id.img_slider);
        Glide.with(context).load(banners.get(position).getLink_img()).into(img_slider);
        container.addView(view);

        if (HomeActivity.defSystemLocale.equals("fa")) {
            view.setRotationY(180);
        }
        return view;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
