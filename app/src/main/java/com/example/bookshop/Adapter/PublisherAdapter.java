package com.example.bookshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Activity.PublisherActivity;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.Publisher;
import com.example.bookshop.R;

import java.util.List;


public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.Holder> {

    List<Publisher> data;
    Context context;


    public PublisherAdapter(List<Publisher> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_publisher,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title_publisher.setText(data.get(position).getName());
        Glide.with(context).load(data.get(position).getLink_img()).into(holder.img_publisher);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView img_publisher;
        TextView title_publisher;


        public Holder(@NonNull View itemView) {
            super(itemView);

            img_publisher = itemView.findViewById(R.id.item_publisher_img);
            title_publisher = itemView.findViewById(R.id.item_publisher_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PublisherActivity.class);
                    intent.putExtra(Key.PUBLISHER_NAME,data.get(getAdapterPosition()).getName());
                    intent.putExtra(Key.PUBLISHER_ID,data.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });


        }
    }
}
