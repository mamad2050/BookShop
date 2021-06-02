package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Model.User;
import com.example.bookshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {


    EditText usernameEditText, mailEditText, passwordEditText, phoneEditText;
    Button registerBTN;
    ApiInterface request;
    String baseUrl = "http://scusad-bookshoponline.fandogh.cloud/api/rest-auth/";

    private static final String TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
        register();
    }

    private void initialize() {

        usernameEditText = findViewById(R.id.registerActivity_edt_user);
        mailEditText = findViewById(R.id.registerActivity_edt_mail);
        passwordEditText = findViewById(R.id.registerActivity_edt_password);
        phoneEditText = findViewById(R.id.registerActivity_edt_phone);
        registerBTN = findViewById(R.id.registerActivity_btn);
        request=ApiClient.getRetrofit(baseUrl).create(ApiInterface.class);

    }

    private void register() {



        String username = usernameEditText.getText().toString().trim();
        String email = mailEditText.getText().toString().trim();
        String password1 = passwordEditText.getText().toString().trim();
        String password2 = passwordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

     registerBTN.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Call<User> userCall = request.register(username,password1,password2);

             userCall.enqueue(new Callback<User>() {

                 @Override
                 public void onResponse(Call<User> call, Response<User> response) {

                     Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_LONG).show();
                 }

                 @Override
                 public void onFailure(Call<User> call, Throwable t) {
                     Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_LONG).show();
                     Log.e(TAG, "onFailure: "+t.getMessage() );
                 }
             });

         }
     });

    }
}