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
import com.example.bookshop.HomeActivity;
import com.example.bookshop.Model.Category;
import com.example.bookshop.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    Context context;
    List<Category> categories;

    public interface ClickedOnCategoryListener {

        void onClick(Category category);

    }

    private CategoryAdapter.ClickedOnCategoryListener listener;

    public void setListener(CategoryAdapter.ClickedOnCategoryListener listener) {

        this.listener = listener;

    }

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

        if (HomeActivity.defSystemLocale.equals("fa")) {
            holder.name_category.setText(categories.get(position).getName_fa());
        }else {
            holder.name_category.setText(categories.get(position).getName_en());
        }

        Glide.with(context).load(categories.get(position).getLink_img())
                .centerCrop().circleCrop().into(holder.img_category);




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

            itemView.setOnClickListener(v -> listener.onClick(categories.get(getAdapterPosition())));



        }
    }
}
