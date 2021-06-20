package com.example.bookshop.Adapter;

import android.annotation.SuppressLint;
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
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.Model.FirstItemOffer;
import com.example.bookshop.Model.Offer;
import com.example.bookshop.R;

import java.util.List;

public class BookNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<Offer> data;

    public BookNewAdapter(Context context, List<Offer> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_book_news, parent, false);
            return new BookNewAdapter.BookNewsViewHolder(view);

        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.item_first_item_news, parent, false);
            return new BookNewAdapter.FirstItemViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == 0) {
            BookOffer bookOffer = (BookOffer) data.get(position).getObject();
            ((BookNewAdapter.BookNewsViewHolder) holder).setBookNewsItems(bookOffer);

        } else {

            FirstItemOffer firstItemOffer = (FirstItemOffer) data.get(position).getObject();
            ((BookNewAdapter.FirstItemViewHolder) holder).setFirstItem(firstItemOffer);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public class FirstItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img_firstItem;
        TextView txt_title_firstItem;

        public FirstItemViewHolder(@NonNull View itemView) {
            super(itemView);

            img_firstItem = itemView.findViewById(R.id.item_firstNews_img);
            txt_title_firstItem = itemView.findViewById(R.id.item_firstNews_title);

        }

        public void setFirstItem(FirstItemOffer firstItemOffer) {

            txt_title_firstItem.setText(firstItemOffer.getTitle());
            Glide.with(context).load(R.drawable.offer).into(img_firstItem);

        }

    }

    /*View Holder for Book News */
    public class BookNewsViewHolder extends RecyclerView.ViewHolder {
        TextView  txt_bookName, txt_final_price;
        ImageView img_book;

        public BookNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_final_price = itemView.findViewById(R.id.item_book_news_finalPrice_txt);
            txt_bookName = itemView.findViewById(R.id.item_book_news_bookName_txt);
            img_book = itemView.findViewById(R.id.item_book_news_img);
        }

        @SuppressLint("SetTextI18n")
        public void setBookNewsItems(BookOffer bookOffer) {

            txt_bookName.setText(bookOffer.getName());

            /*set Final Price*/
            int finalPrice = Integer.parseInt(bookOffer.getPrice());
            txt_final_price.setText(DecimalFormatter.formatted(finalPrice) );

            Glide.with(context).load(bookOffer.getLink_img()).into(img_book);


        }
    }
}
