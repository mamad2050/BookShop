package com.example.bookshop.Adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Global.DecimalFormatter;
import com.example.bookshop.Model.Book;
import com.example.bookshop.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.Holder> {


    Context context;
    List<Book> data;


    public BookAdapter(Context context, List<Book> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BookAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookAdapter.Holder(LayoutInflater.from(context).inflate(R.layout.item_popular, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        int final_price = Integer.parseInt(data.get(position).getFinal_price());

        holder.txt_finalPrice.setText(DecimalFormatter.formatted(final_price));

        holder.txt_name.setText(data.get(position).getName());
        holder.txt_author.setText(data.get(position).getAuthor());
        Glide.with(context).load(data.get(position).getLink_img()).into(holder.img_book);


        /*FullPrice For Discountable Books*/
        if (data.get(position).getFinal_price().equals(data.get(position).getPrice())) {
            holder.txt_price.setText("");
        }

        else {
            int price = Integer.parseInt(data.get(position).getPrice());
            SpannableString spannableString = new SpannableString(DecimalFormatter.formatted(price));
            spannableString.setSpan(new StrikethroughSpan(), 0, data.get(position).getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.txt_price.setText(spannableString);
        }

        /*Discount*/

        if (data.get(position).getDiscount().equals("0") ) {
            holder.txt_discount.setVisibility(View.GONE);
        }else
        {
            int discount = Integer.parseInt(data.get(position).getDiscount());
            holder.txt_discount.setText(DecimalFormatter.formatted(discount)+"%");
        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView img_book;
        TextView txt_name;
        TextView txt_finalPrice;
        TextView txt_price;
        TextView txt_author;
        TextView txt_discount;


        public Holder(@NonNull View itemView) {
            super(itemView);

            img_book = itemView.findViewById(R.id.item_populars_img);
            txt_name = itemView.findViewById(R.id.item_populars_book_name);
            txt_author = itemView.findViewById(R.id.item_populars_author);
            txt_finalPrice = itemView.findViewById(R.id.item_populars_final_price);
            txt_price = itemView.findViewById(R.id.item_populars_price);
            txt_discount = itemView.findViewById(R.id.item_populars_discount);

        }
    }

}
