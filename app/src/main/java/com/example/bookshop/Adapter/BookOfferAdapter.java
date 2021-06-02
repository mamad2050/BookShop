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
import com.example.bookshop.Model.Offer;
import com.example.bookshop.Model.BookOffer;
import com.example.bookshop.Model.FirstItemOffer;
import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
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

        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.item_first_item_offer, parent, false);
            return new FirstItemViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == 0) {
            BookOffer bookOffer = (BookOffer) data.get(position).getObject();
            ((BookOfferViewHolder) holder).setBookOfferItems(bookOffer);

        } else {

            FirstItemOffer firstItemOffer = (FirstItemOffer) data.get(position).getObject();
            ((FirstItemViewHolder) holder).setFirstItem(firstItemOffer);
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
    public  class FirstItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img_firstAmazing;
        TextView txt_title_firstAmazing;

        public FirstItemViewHolder(@NonNull View itemView) {
            super(itemView);

            img_firstAmazing = itemView.findViewById(R.id.item_firstAmazing_img);
            txt_title_firstAmazing = itemView.findViewById(R.id.item_firstAmazing_title);

        }

        public void setFirstItem(FirstItemOffer firstItemOffer) {

            txt_title_firstAmazing.setText(firstItemOffer.getTitle());
            Picasso.get().load(firstItemOffer.getLink_img()).into(img_firstAmazing);

        }

    }


    /*View Holder for Book Offer */
    public  class BookOfferViewHolder extends RecyclerView.ViewHolder {
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

        public void setBookOfferItems(BookOffer bookOffer) {


            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            String price = decimalFormat.format(Integer.valueOf(bookOffer.getPrice()));
            txt_bookName.setText(bookOffer.getName());

            txt_discount.setText(bookOffer.getDiscount() + "%");
            txt_final_price.setText(bookOffer.getFinal_price());

            SpannableString spannableString = new SpannableString(price);
            spannableString.setSpan(new StrikethroughSpan(),0,bookOffer.getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_price.setText(spannableString);


            Glide.with(context).load(bookOffer.getLink_img()).into(img_book);


        }
    }
}
