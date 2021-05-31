package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Model.Category;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name_category.setText(categories.get(position).getName());
        Picasso.get().load(categories.get(position).getLink_img()).into(holder.img_category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView img_category;
        TextView name_category;

        public Holder(@NonNull View itemView) {
            super(itemView);

            img_category = itemView.findViewById(R.id.item_category_img);
            name_category = itemView.findViewById(R.id.item_category_name);
        }
    }
}
