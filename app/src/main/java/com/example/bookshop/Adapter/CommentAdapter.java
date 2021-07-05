package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Model.Comment;
import com.example.bookshop.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {

    Context context;
    List<Comment> data;

    public CommentAdapter(Context context, List<Comment> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.txt_rating.setText(data.get(position).getRating());
        holder.txt_title.setText(data.get(position).getTitle());
        holder.txt_description.setText(data.get(position).getDescription());
        holder.txt_date.setText(data.get(position).getDate());
        holder.txt_username.setText(data.get(position).getUsername_user());

        String rate = data.get(position).getRating();

        if (rate.startsWith("2") || rate.startsWith("1")) {
            holder.txt_rating.setBackgroundResource(R.drawable.bg_rate_red);
        } else if (rate.startsWith("3")) {
            holder.txt_rating.setBackgroundResource(R.drawable.bg_rate_orange);
        } else if (rate.startsWith("4")) {
            holder.txt_rating.setBackgroundResource(R.drawable.bg_rate_blue);
        } else if (rate.startsWith("5")) {
            holder.txt_rating.setBackgroundResource(R.drawable.bg_rate_green);
        }

        String positive = data.get(position).getPositive();
        if (!positive.isEmpty()) {
            holder.layout_positive.setVisibility(View.VISIBLE);
            holder.txt_positive.setText(data.get(position).getPositive());
        } else {
            holder.txt_positive.setVisibility(View.GONE);
        }

        String negative = data.get(position).getNegative();
        if (!negative.isEmpty()) {
            holder.layout_negative.setVisibility(View.VISIBLE);
            holder.txt_negative.setText(data.get(position).getNegative());
        } else {
            holder.txt_negative.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {


        TextView txt_rating, txt_username, txt_date, txt_description, txt_positive, txt_negative, txt_title;
        LinearLayout layout_positive;
        LinearLayout layout_negative;

        public Holder(@NonNull View itemView) {
            super(itemView);


            txt_date = itemView.findViewById(R.id.item_comment_txt_date);
            txt_rating = itemView.findViewById(R.id.item_comment_txt_rating);
            txt_username = itemView.findViewById(R.id.item_comment_txt_username);
            txt_description = itemView.findViewById(R.id.item_comment_limit_txt_description);
            txt_title = itemView.findViewById(R.id.item_comment_txt_title);
            txt_positive = itemView.findViewById(R.id.item_comment_txt_positive);
            txt_negative = itemView.findViewById(R.id.item_comment_txt_negative);
            layout_positive = itemView.findViewById(R.id.item_comment_layout_txt_positive);
            layout_negative = itemView.findViewById(R.id.item_comment_layout_txt_negative);

        }
    }
}
