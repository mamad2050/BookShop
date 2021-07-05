package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Global.Key;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.Model.Message;
import com.example.bookshop.R;
import com.hedgehog.ratingbar.RatingBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class SendCommentActivity extends AppCompatActivity {

    EditText edt_title, edt_description, edt_positive, edt_negative;
    Button btn_send;
    ImageView img_book;
    TextView txt_book;
    Bundle bundle;
    RatingBar ratingBar;
    ApiInterface request;
    static String rating_for_send, username_for_send, id, date;

    MyPreferencesManager myPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_comment);

        initialize();


        ratingBar.setOnRatingChangeListener(RatingCount -> rating_for_send = String.valueOf(RatingCount));


        btn_send.setOnClickListener(e -> {

            username_for_send = myPreferencesManager.getUserData().get(MyPreferencesManager.USERNAME);

            String title = edt_title.getText().toString();
            String description = edt_description.getText().toString();
            String positive = edt_positive.getText().toString();
            String negative = edt_negative.getText().toString();

            PersianDate pDate = new PersianDate();
            PersianDateFormat format = new PersianDateFormat("Y/m/d");
            date = format.format(pDate);

            sendComment(id, username_for_send, title, description, positive, negative, date, rating_for_send);


        });


    }

    private void initialize() {


        bundle = getIntent().getExtras();

        myPreferencesManager = new MyPreferencesManager(this);

        request = ApiClient.getRetrofit().create(ApiInterface.class);

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
        id = bundle.getString(Key.ID);

    }


    private void sendComment(String id, String username_for_send, String title,
                             String description, String positive, String negative,
                             String date, String rating_for_send) {
        Call<Message> messageCall = request.sendCommentCall(id, username_for_send, description,
                rating_for_send, positive, negative, title, date);

        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                if (response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(SendCommentActivity.this, "نظر شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();

                    edt_description.setText("");
                    edt_negative.setText("");
                    edt_positive.setText("");
                    edt_title.setText("");
                    ratingBar.setStar(0f);

                    new Handler().postDelayed(() -> onBackPressed(), 2000);


                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

                Toast.makeText(SendCommentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}