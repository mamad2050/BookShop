package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Global.DecimalFormatter;
import com.example.bookshop.Model.Cart;
import com.example.bookshop.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {


    Context context;
    List<Cart> carts;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.Holder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.txt_name.setText(carts.get(position).getName());
        Glide.with(context).load(carts.get(position).getLink_img()).into(holder.img_book);

        int price = Integer.parseInt(carts.get(position).getFinal_price());
        holder.txt_final_price.setText(DecimalFormatter.convert(price));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_count, txt_final_price;
        ImageView img_book;
        LinearLayout layout_delete;

        public Holder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.item_cart_book_name);
            txt_count = itemView.findViewById(R.id.item_cart_count);
            txt_final_price = itemView.findViewById(R.id.final_price_cart);
            txt_name = itemView.findViewById(R.id.item_cart_book_name);
            img_book = itemView.findViewById(R.id.item_cart_img);
            layout_delete = itemView.findViewById(R.id.lay_delete_cart);
        }
    }

}
