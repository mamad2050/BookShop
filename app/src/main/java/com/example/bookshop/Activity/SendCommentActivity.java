package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.R;
import com.hedgehog.ratingbar.RatingBar;

public class SendCommentActivity extends AppCompatActivity {

    EditText edt_title, edt_description, edt_positive, edt_negative;
    Button btn_send;
    ImageView img_book;
    TextView txt_book;
    Bundle bundle;
    RatingBar ratingBar;
    static String rating_for_send , username_for_send ;

    MyPreferencesManager myPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);

        bundle = getIntent().getExtras();

        myPreferencesManager = new MyPreferencesManager(this);

        img_book = findViewById(R.id.activity_send_comment_img_book);
        txt_book = findViewById(R.id.activity_send_comment_txt_book);
        edt_title = findViewById(R.id.activity_send_comment_edt_title);
        edt_description = findViewById(R.id.activity_send_comment_edt_description);
        edt_positive = findViewById(R.id.activity_send_comment_edt_positive);
        edt_negative = findViewById(R.id.activity_send_comment_edt_negative);
        btn_send = findViewById(R.id.activity_send_comment_btn_send);
        ratingBar = findViewById(R.id.ratingbar);

        Glide.with(this).load(bundle.getString(Key.BOOK_IMG)).into(img_book);
        txt_book.setText(bundle.getString(Key.BOOK_NAME));

        ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {

                rating_for_send = String.valueOf(RatingCount);
            }
        });


    }
}