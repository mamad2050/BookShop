package com.example.bookshop.Adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Model.Question;

import java.util.List;
import com.example.bookshop.R;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.Holder> {


    Context context;
    List<Question> questions;

    public QuestionAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_question,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        holder.txt_title.setText(questions.get(position).getTitle());
        holder.txt_description.setText(questions.get(position).getDescription());


        holder.img.setOnClickListener(event -> {


            if (holder.parent_description.getVisibility() == View.GONE ) {

                holder.parent_description.setVisibility(View.VISIBLE);
                holder.img.setImageResource(R.drawable.ic_baseline_close_24);
                TransitionManager.beginDelayedTransition(holder.parent_title,new AutoTransition());

            }else{

                holder.parent_description.setVisibility(View.GONE);
                holder.img.setImageResource(R.drawable.ic_baseline_add_24);
                TransitionManager.beginDelayedTransition(holder.parent_title,new AutoTransition());

            }

        });


    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        RelativeLayout parent_title, parent_description;
        TextView txt_title, txt_description;
        ImageView img;


        public Holder(@NonNull View itemView) {
            super(itemView);



            txt_description = itemView.findViewById(R.id.activity_question_txt_description);
            txt_title = itemView.findViewById(R.id.activity_question_txt_title);

            parent_description = itemView.findViewById(R.id.activity_question_parent_description);
            parent_title = itemView.findViewById(R.id.activity_question_parent_title);

            img = itemView.findViewById(R.id.activity_question_img);






        }
    }
}
