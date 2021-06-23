package com.example.bookshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Activity.DetailCategoryActivity;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.Category;
import com.example.bookshop.R;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.Holder> {

    Context context;
    List<Category> data;

    public interface ClickedOnCategoryListener {

        void onClick(Category category);

    }

    private ClickedOnCategoryListener listener;

    public void setListener(ClickedOnCategoryListener listener) {

        this.listener = listener;

    }


    public AllCategoryAdapter(Context context, List<Category> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_all_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title_category.setText(data.get(position).getName_fa());
        Glide.with(context).load(data.get(position).getLink_img()).into(holder.img_category);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView img_category;
        TextView title_category;

        public Holder(@NonNull View itemView) {
            super(itemView);

            img_category = itemView.findViewById(R.id.item_all_category_img);
            title_category = itemView.findViewById(R.id.item_all_category_title);

            itemView.setOnClickListener(v -> listener.onClick(data.get(getAdapterPosition())));

        }
    }

}
