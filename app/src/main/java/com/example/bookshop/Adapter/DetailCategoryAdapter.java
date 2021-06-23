package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.R;

import java.util.List;

public class DetailCategoryAdapter extends RecyclerView.Adapter<DetailCategoryAdapter.Holder> {

    Context context;
    List<BookOffer> data;

    public DetailCategoryAdapter(Context context, List<BookOffer> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name_book.setText(data.get(position).getName());
        Glide.with(context).load(data.get(position).getLink_img()).into(holder.img_book);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView img_book;
        TextView name_book;

        public Holder(@NonNull View itemView) {
            super(itemView);


            img_book = itemView.findViewById(R.id.item_recyclerview_category_img);
            name_book = itemView.findViewById(R.id.item_recyclerview_category_name);
        }
    }
}
