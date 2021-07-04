package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bookshop.Adapter.CommentAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Model.Comment;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {


    ImageView img_book;
    TextView txt_book;
    Bundle bundle;
   LinearLayout send;
    RequestQueue requestQueue;
    List<Comment> comments = new ArrayList<>();
    CommentAdapter commentAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        bundle = getIntent().getExtras();
        requestQueue = Volley.newRequestQueue(this);
        img_book = findViewById(R.id.activity_send_comment_img_book);
        txt_book = findViewById(R.id.activity_send_comment_txt_book);
        send = findViewById(R.id.activity_comment_layout_send_comment);


        Glide.with(this).load(bundle.getString(Key.BOOK_IMG)).into(img_book);
        txt_book.setText(bundle.getString(Key.BOOK_NAME));


        send.setOnClickListener(event -> {

            Intent intent = new Intent(CommentActivity.this,SendCommentActivity.class);
            intent.putExtra(Key.ID, bundle.getString(Key.ID));
            intent.putExtra(Key.BOOK_NAME,bundle.getString(Key.BOOK_NAME));
            intent.putExtra(Key.BOOK_IMG,bundle.getString(Key.BOOK_IMG));
            startActivity(intent);


        });


        getComment(bundle.getString(Key.ID));

    }


    private void getComment(String id) {



        recyclerView = findViewById(R.id.activity_comment_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(this, comments);
        recyclerView.setAdapter(commentAdapter);

        Response.Listener <String> listener = response -> {

            Gson gson = new Gson();
            Comment[] commentsArray = gson.fromJson( response, Comment[].class);

            comments.addAll(Arrays.asList(commentsArray));
            commentAdapter.notifyDataSetChanged();


        };

        Response.ErrorListener errorListener = error -> {

            Toast.makeText(getApplicationContext(), error.getMessage() + "", Toast.LENGTH_SHORT).show();
            Log.d("Error : ", error.getMessage() + "");

        };

        StringRequest request = new StringRequest(Request.Method.POST, Constants.GET_COMMENTS, listener, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put(Key.ID, id);
                return map;

            }
        };
        requestQueue.add(request);

    }
}