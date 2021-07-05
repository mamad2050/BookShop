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

public class CommentLimitAdapter extends RecyclerView.Adapter<CommentLimitAdapter.Holder> {

    Context context;
    List<Comment> data;

    public CommentLimitAdapter(Context context, List<Comment> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_comment_limit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        holder.txt_title.setText(data.get(position).getTitle());
        holder.txt_description.setText(data.get(position).getDescription());
        holder.txt_date.setText(data.get(position).getDate());
        holder.txt_username.setText(data.get(position).getUsername_user());

        String suggest = data.get(position).getSuggest();

        if (suggest.equals("1")) {

            holder.layout_positive.setVisibility(View.VISIBLE);
        }

        else {
            holder.layout_negative.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {


        TextView txt_username, txt_date, txt_description, txt_title;
        LinearLayout layout_positive;
        LinearLayout layout_negative;

        public Holder(@NonNull View itemView) {
            super(itemView);


            txt_date = itemView.findViewById(R.id.item_comment_limit_txt_date);
            txt_username = itemView.findViewById(R.id.item_comment_limit_txt_user);
            txt_description = itemView.findViewById(R.id.item_comment_limit_txt_description);
            txt_title = itemView.findViewById(R.id.item_comment_limit_txt_title);
            layout_positive = itemView.findViewById(R.id.item_comment_limit_layout_suggest_positive);
            layout_negative = itemView.findViewById(R.id.item_comment_limit_layout_suggest_negative);

        }
    }
}
