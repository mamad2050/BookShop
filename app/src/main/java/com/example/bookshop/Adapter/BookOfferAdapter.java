package com.example.bookshop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.bookshop.Activity.BookActivity;
import com.example.bookshop.Global.DecimalFormatter;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.LastItem;
import com.example.bookshop.Model.ObjectOffer;
import com.example.bookshop.Model.Book;
import com.example.bookshop.Model.FirstItemOffer;
import com.example.bookshop.R;

import java.util.List;


public class BookOfferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ObjectOffer> data;

    public BookOfferAdapter(Context context, List<ObjectOffer> data) {
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
            Book book = (Book) data.get(position).getObject();
            ((BookOfferViewHolder) holder).setBookOfferItems(book);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, BookActivity.class);

                intent.putExtra(Key.ID, ((Book) data.get(position).getObject()).getId());

                context.startActivity(intent);
            });


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
        TextView txt_discount, txt_price, txt_bookName, txt_final_price ,txt_author;
        ImageView img_book;

        public BookOfferViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_discount = itemView.findViewById(R.id.item_book_offer_discount_txt);
            txt_price = itemView.findViewById(R.id.item_book_offer_price_txt);
            txt_final_price = itemView.findViewById(R.id.item_book_offer_finalPrice_txt);
            txt_bookName = itemView.findViewById(R.id.item_book_offer_bookName_txt);
            img_book = itemView.findViewById(R.id.item_book_offer_img);
            txt_author = itemView.findViewById(R.id.item_book_offer_author_txt);
        }

        @SuppressLint("SetTextI18n")
        public void setBookOfferItems(Book book) {

            txt_bookName.setText(book.getName());
            txt_author.setText(book.getAuthor());

            int discount = Integer.parseInt(book.getDiscount());
            txt_discount.setText(DecimalFormatter.convert(discount) + "%");

            /*set Final Price*/
            int finalPrice = Integer.parseInt(book.getFinal_price());
            txt_final_price.setText(DecimalFormatter.convert(finalPrice));


            /*Scratch Original Price*/
            int price = Integer.parseInt(book.getPrice());
            SpannableString spannableString = new SpannableString(DecimalFormatter.convert(price));
            spannableString.setSpan(new StrikethroughSpan(), 0, book.getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            txt_price.setText(spannableString);

            Glide.with(context).load(book.getLink_img()).into(img_book);


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
