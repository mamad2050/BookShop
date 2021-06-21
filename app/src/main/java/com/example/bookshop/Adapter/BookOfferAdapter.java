package com.example.bookshop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.fonts.Font;
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
import com.example.bookshop.Model.LastItem;
import com.example.bookshop.Model.Offer;
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.Model.FirstItemOffer;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BookOfferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Offer> data;

    public BookOfferAdapter(Context context, List<Offer> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_book_offer, parent, false);
            return new BookOfferViewHolder(view);

        } else if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_first_item_offer, parent, false);
            return new FirstItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_show_all_book, parent, false);
            return new BookOfferAdapter.EndItemViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == 0) {
            BookOffer bookOffer = (BookOffer) data.get(position).getObject();
            ((BookOfferViewHolder) holder).setBookOfferItems(bookOffer);

        } else if (getItemViewType(position) == 1) {

            FirstItemOffer firstItemOffer = (FirstItemOffer) data.get(position).getObject();
            ((FirstItemViewHolder) holder).setFirstItem(firstItemOffer);
        } else if (getItemViewType(position) == 2) {
            LastItem lastItem = (LastItem) data.get(position).getObject();
            ((BookOfferAdapter.EndItemViewHolder) holder).setLastItem(lastItem);
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

    /*View Holder For First Item RecyclerView*/
    public class FirstItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img_firstAmazing;
        TextView txt_title_firstAmazing;

        public FirstItemViewHolder(@NonNull View itemView) {
            super(itemView);

            img_firstAmazing = itemView.findViewById(R.id.item_firstAmazing_img);
            txt_title_firstAmazing = itemView.findViewById(R.id.item_firstAmazing_title);

        }

        public void setFirstItem(FirstItemOffer firstItemOffer) {

            txt_title_firstAmazing.setText(firstItemOffer.getTitle());
            Glide.with(context).load(firstItemOffer.getLink_img()).into(img_firstAmazing);

        }

    }


    /*View Holder for Book Offer */
    public class BookOfferViewHolder extends RecyclerView.ViewHolder {
        TextView txt_discount, txt_price, txt_bookName, txt_final_price;
        ImageView img_book;

        public BookOfferViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_discount = itemView.findViewById(R.id.item_book_offer_discount_txt);
            txt_price = itemView.findViewById(R.id.item_book_offer_price_txt);
            txt_final_price = itemView.findViewById(R.id.item_book_offer_finalPrice_txt);
            txt_bookName = itemView.findViewById(R.id.item_book_offer_bookName_txt);
            img_book = itemView.findViewById(R.id.item_book_offer_img);
        }

        @SuppressLint("SetTextI18n")
        public void setBookOfferItems(BookOffer bookOffer) {

            txt_bookName.setText(bookOffer.getName());


            int discount = Integer.parseInt(bookOffer.getDiscount());
            txt_discount.setText(DecimalFormatter.formatted(discount) + "%");

            /*set Final Price*/
            int finalPrice = Integer.parseInt(bookOffer.getFinal_price());
            txt_final_price.setText(DecimalFormatter.formatted(finalPrice));


            /*Scratch Original Price*/
            int price = Integer.parseInt(bookOffer.getPrice());
            SpannableString spannableString = new SpannableString(DecimalFormatter.formatted(price));
            spannableString.setSpan(new StrikethroughSpan(), 0, bookOffer.getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_price.setText(spannableString);

            Glide.with(context).load(bookOffer.getLink_img()).into(img_book);


        }
    }

    public class EndItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img_lastItem;
        TextView txt_lastItem;

        public EndItemViewHolder(@NonNull View itemView) {
            super(itemView);

            img_lastItem = itemView.findViewById(R.id.item_show_all_book_img);
            txt_lastItem = itemView.findViewById(R.id.item_show_all_book_title);

        }

        public void setLastItem(LastItem lastItem) {

            txt_lastItem.setText(lastItem.getTitle());
//            Glide.with(context).load(lastItem.getImg_link()).into(img_lastItem);

        }
    }
}
