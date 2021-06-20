package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Model.Banner;
import com.example.bookshop.R;

import java.util.List;

public class SecondBannerAdapter extends RecyclerView.Adapter<SecondBannerAdapter.Holder> {

    Context context;
    List<Banner> banners;

    public SecondBannerAdapter(Context context, List<Banner> banners) {
        this.context = context;
        this.banners = banners;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_banner_second, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Glide.with(context).load(banners.get(position).getLink_img()).into(holder.img_banner);

    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView img_banner;

        public Holder(@NonNull View itemView) {
            super(itemView);

            img_banner = itemView.findViewById(R.id.item_banner_second_img_banner);
        }
    }
}
