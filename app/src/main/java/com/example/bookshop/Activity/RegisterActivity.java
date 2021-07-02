package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Model.Users;
import com.example.bookshop.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    EditText usernameEditText, mailEditText, passwordEditText, phoneEditText;
    TextView txt_login;
    Button registerBTN;
    ApiInterface request;


    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();


        txt_login.setOnClickListener(event -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));


        registerBTN.setOnClickListener(v -> {


            String username = usernameEditText.getText().toString().trim();
            String email = mailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();


            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()){
                Toast.makeText(this, "لطفا همه فیلد هارا پر کنید", Toast.LENGTH_SHORT).show();
            } else{
                register(username, email, phone, password);
            }

        });

    }

    private void initialize() {

        usernameEditText = findViewById(R.id.registerActivity_edt_user);
        mailEditText = findViewById(R.id.registerActivity_edt_mail);
        passwordEditText = findViewById(R.id.registerActivity_edt_password);
        phoneEditText = findViewById(R.id.registerActivity_edt_phone);
        registerBTN = findViewById(R.id.registerActivity_btn);
        txt_login = findViewById(R.id.register_activity_txt_login);
        request = ApiClient.getRetrofit().create(ApiInterface.class);

    }

    private void register(String username, String email, String phone, String password) {


        Call<Users> usersCall = request.registerCall(username,email, phone, password);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if (response.isSuccessful() && response.body().isStatus()) {

                    Toast.makeText(RegisterActivity.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}



